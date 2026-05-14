package com.lucas.pingwise.application.usecases.check;

import com.lucas.pingwise.application.ports.in.check.DispatchDueChecksUseCase;
import com.lucas.pingwise.application.ports.out.MonitorRepository;
import com.lucas.pingwise.application.ports.out.ProducerPort;
import com.lucas.pingwise.domain.model.Monitor;
import com.lucas.pingwise.infrastructure.messaging.MonitorJobMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Slf4j
@RequiredArgsConstructor
@Service
public class DispatchDueChecksUseCaseImpl implements DispatchDueChecksUseCase {

    private final MonitorRepository monitorRepository;
    private final ProducerPort producerPort;
    private final MonitorJobMapper monitorJobMapper;

    private static final int PAGE_SIZE = 20;
    private static final int QUEUE_GAP = 2000;
    private static final int N_CONSUMERS = 2;

    @Override
    public void execute() {
        try {
            int offset = 0;
            List<Monitor> batch;
            BlockingQueue<Monitor> queue = new ArrayBlockingQueue<>(QUEUE_GAP);
            ExecutorService pool = newFixedThreadPool(N_CONSUMERS);

            do {
                batch = this.monitorRepository.findDueMonitors(offset, PAGE_SIZE);

                for (Monitor monitor : batch) {
                    queue.put(monitor);
                }

                offset += PAGE_SIZE;

            } while (batch.size() == PAGE_SIZE);


            for (int i=0; i < N_CONSUMERS; i++) {
               pool.submit(() -> {
                   while (true) {
                       Monitor monitor = queue.poll();

                       if (monitor == null) break;

                       log.info("Current monitor: {}, at thread: {}", monitor, Thread.currentThread());

                       producerPort.sendCheckMessageProducer(this.monitorJobMapper.toCheckJob(monitor));
                       monitor.scheduleNextCheck();

                       // TODO: Implement a better way to save this changes, this is not performatic with a lot of monitors.
                       monitorRepository.save(monitor);
                   }
               });
            }


            pool.shutdown();
            final var terminated = pool.awaitTermination(40, TimeUnit.SECONDS);

            if (!terminated) {
                throw new RuntimeException("Pool did not terminate");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
