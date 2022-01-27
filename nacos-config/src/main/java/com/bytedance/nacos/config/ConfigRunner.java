package com.bytedance.nacos.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author: movie
 * @date: 2022/1/25 17:46
 */
//@Component
public class ConfigRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
//    @Resource
//    private NacosConfigManager nacosConfigManager;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        nacosConfigManager.getConfigService().addListener(
//                "application.yml",
//                "DEFAULT_GROUP",
//                new Listener() {
//                    @Override
//                    public Executor getExecutor() {
//                        return null;
//                    }
//
//                    @Override
//                    public void receiveConfigInfo(String configInfo) {
//                        Properties properties = new Properties();
//                        try {
//                            properties.load(new StringReader(configInfo));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } finally {
//                            System.out.println("config changed" + properties);
//                        }
//                    }
//                }
//        );
//    }
}
