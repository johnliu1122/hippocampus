package org.dreamlife.hippocampus.performance;

import org.dreamlife.hippocampus.performance.record.RestApiRecorder;
import org.dreamlife.hippocampus.performance.service.PerformanceSummaryService;
import org.dreamlife.hippocampus.performance.sink.SinkPerformanceScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 性能统计服务
 * @auther 柳俊阳
 * @github https://github.com/johnliu1122/
 * @csdn https://blog.csdn.net/qq_35695616
 * @email johnliu1122@163.com
 * @date 2020/3/30
 */
@Configuration
public class PerformanceAutoConfiguration {

    @Bean
    public PerformanceSummaryService performanceSummaryService(){
        /**
         * 配置并行度参数
         * 该参数决定fork的消费者线程数以及容器数
         */
        int concurrencyLevel = 2;
        return new PerformanceSummaryService(concurrencyLevel);
    }

    /**
     * 设置切面来采集RestApi的接口响应时间
     * @return
     */
    @Bean
    public RestApiRecorder restApiRecord(){
        return new RestApiRecorder();
    }

    /**
     * 定时持久化各个API的性能指标
     * @return
     */
    @Bean
    public SinkPerformanceScheduler sink(){
        return new SinkPerformanceScheduler();
    }

}
