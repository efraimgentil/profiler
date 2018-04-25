package me.efraimgentil.profiler;

import me.efraimgentil.profiler.config.ProfilerAutoConfiguration;
import me.efraimgentil.profiler.config.ProfilerProperties;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

public class ProfilerApplicationTests {

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

	@Test
	public void shouldContainProfilerAspectBean() {
		contextRunner
				.withUserConfiguration(ProfilerAutoConfiguration.class)
				.run(context -> {
			Assertions.assertThat(context).hasSingleBean(ProfilerAspect.class);
			Assertions.assertThat(context).hasSingleBean(ProfilerProperties.class);
		});
	}

}
