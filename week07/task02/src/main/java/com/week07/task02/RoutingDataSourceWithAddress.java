package com.week07.task02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

@Slf4j
public class RoutingDataSourceWithAddress extends AbstractRoutingDataSource {

    public RoutingDataSourceWithAddress(DataSource defaultDataSource, Map<Object, Object> targetDataSource) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        final DataSourceAddressEnum currentDataSource = DataSourceContextHolder.getCurrentDataSource();
        if (log.isDebugEnabled()) {
            log.debug("routing data source address is {}", currentDataSource.name());
        }

        return currentDataSource;

    }
}
