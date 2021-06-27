package com.week07.task02;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceAddressEnum> CONTEXT_HOLDER =
            ThreadLocal.withInitial(() -> DataSourceAddressEnum.MASTER);

    public static void setCurrentDataSource(DataSourceAddressEnum dataSourceAddressEnum) {
        CONTEXT_HOLDER.set(dataSourceAddressEnum);
    }

    public static void removeDataSource() {
        CONTEXT_HOLDER.remove();
    }

    public static DataSourceAddressEnum getCurrentDataSource() {
        return CONTEXT_HOLDER.get();
    }
}
