package kr.or.ddit.batch;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

public class YogultTask {
	private static final Logger logger = LoggerFactory.getLogger(YogultTask.class);
	
	@Resource(name = "jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name = "yogultJob")
	private Job yogultJob;
	
	// 주기별로 스케줄러에서 실행될 메서드 만들기
	public void yogultTask() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		logger.debug("=====================yogultTask=====================");
		jobLauncher.run(yogultJob, new JobParameters());
	}
}
