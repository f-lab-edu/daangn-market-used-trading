package com.ssibongee.daangnmarket.commons.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.ssibongee.daangnmarket.commons.util.DatabaseType.*;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly() ? SLAVE : MASTER;
    }
}
