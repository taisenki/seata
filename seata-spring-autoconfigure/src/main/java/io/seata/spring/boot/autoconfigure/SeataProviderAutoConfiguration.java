/*
 *  Copyright 1999-2019 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package io.seata.spring.boot.autoconfigure;

import io.seata.spring.boot.autoconfigure.properties.SeataProperties;
import io.seata.spring.boot.autoconfigure.properties.client.*;
import io.seata.spring.boot.autoconfigure.properties.config.*;
import io.seata.spring.boot.autoconfigure.properties.registry.*;
import io.seata.spring.boot.autoconfigure.provider.SpringApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static io.seata.common.Constants.BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER;
import static io.seata.spring.boot.autoconfigure.StarterConstants.*;

/**
 * @author xingfudeshi@gmail.com
 */
@ComponentScan(basePackages = "io.seata.spring.boot.autoconfigure.properties")
@ConditionalOnProperty(prefix = StarterConstants.SEATA_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableConfigurationProperties({SeataProperties.class})
public class SeataProviderAutoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeataProviderAutoConfiguration.class);

    public SeataProviderAutoConfiguration(SeataProperties seataProperties,
                                  RmProperties rmProperties, TmProperties tmProperties, LockProperties lockProperties,
                                  ServiceProperties serviceProperties, ShutdownProperties shutdownProperties, ThreadFactoryProperties threadFactoryProperties,
                                  UndoProperties undoProperties, LogProperties logProperties, TransportProperties transportProperties,
                                  ConfigProperties configProperties, ConfigFileProperties configFileProperties, RegistryProperties registryProperties,
                                  ConfigNacosProperties configNacosProperties, ConfigConsulProperties configConsulProperties, ConfigZooKeeperProperties configZooKeeperProperties,
                                  ConfigApolloProperties configApolloProperties, ConfigEtcd3Properties configEtcd3Properties, ConfigCustomProperties configCustomProperties,
                                  RegistryConsulProperties registryConsulProperties, RegistryEtcd3Properties registryEtcd3Properties, RegistryEurekaProperties registryEurekaProperties,
                                  RegistryNacosProperties registryNacosProperties, RegistryRedisProperties registryRedisProperties, RegistrySofaProperties registrySofaProperties,
                                  RegistryZooKeeperProperties registryZooKeeperProperties, RegistryCustomProperties registryCustomProperties) {
        PROPERTY_BEAN_MAP.put(SEATA_PREFIX, seataProperties);

        PROPERTY_BEAN_MAP.put(CLIENT_RM_PREFIX, rmProperties);
        PROPERTY_BEAN_MAP.put(CLIENT_TM_PREFIX, tmProperties);
        PROPERTY_BEAN_MAP.put(LOCK_PREFIX, lockProperties);
        PROPERTY_BEAN_MAP.put(SERVICE_PREFIX, serviceProperties);
        PROPERTY_BEAN_MAP.put(SHUTDOWN_PREFIX, shutdownProperties);
        PROPERTY_BEAN_MAP.put(THREAD_FACTORY_PREFIX, threadFactoryProperties);
        PROPERTY_BEAN_MAP.put(UNDO_PREFIX, undoProperties);
        PROPERTY_BEAN_MAP.put(LOG_PREFIX, logProperties);
        PROPERTY_BEAN_MAP.put(TRANSPORT_PREFIX, transportProperties);
        PROPERTY_BEAN_MAP.put(CONFIG_PREFIX, configProperties);
        PROPERTY_BEAN_MAP.put(CONFIG_FILE_PREFIX, configFileProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_PREFIX, registryProperties);

        PROPERTY_BEAN_MAP.put(CONFIG_NACOS_PREFIX, configNacosProperties);
        PROPERTY_BEAN_MAP.put(CONFIG_CONSUL_PREFIX, configConsulProperties);
        PROPERTY_BEAN_MAP.put(CONFIG_ZK_PREFIX, configZooKeeperProperties);
        PROPERTY_BEAN_MAP.put(CONFIG_APOLLO_PREFIX, configApolloProperties);
        PROPERTY_BEAN_MAP.put(CONFIG_ETCD3_PREFIX, configEtcd3Properties);
        PROPERTY_BEAN_MAP.put(CONFIG_CUSTOM_PREFIX, configCustomProperties);

        PROPERTY_BEAN_MAP.put(REGISTRY_CONSUL_PREFIX, registryConsulProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_ETCD3_PREFIX, registryEtcd3Properties);
        PROPERTY_BEAN_MAP.put(REGISTRY_EUREKA_PREFIX, registryEurekaProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_NACOS_PREFIX, registryNacosProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_REDIS_PREFIX, registryRedisProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_SOFA_PREFIX, registrySofaProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_ZK_PREFIX, registryZooKeeperProperties);
        PROPERTY_BEAN_MAP.put(REGISTRY_CUSTOM_PREFIX, registryCustomProperties);
    }

    @Bean(BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER)
    @ConditionalOnMissingBean(name = {BEAN_NAME_SPRING_APPLICATION_CONTEXT_PROVIDER})
    public SpringApplicationContextProvider springApplicationContextProvider() {
        return new SpringApplicationContextProvider();
    }
}
