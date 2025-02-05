package batch.transaction.infra.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


public class Base {
    public PagingQueryProvider customQueryProvider(DataSource dataSource, String select,String from, String where, String order) throws Exception {
        SqlPagingQueryProviderFactoryBean queryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();

        queryProviderFactoryBean.setDataSource(dataSource);

        queryProviderFactoryBean.setSelectClause(select);
        queryProviderFactoryBean.setFromClause(from);
        queryProviderFactoryBean.setWhereClause(where);

        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put(order, Order.ASCENDING);

        queryProviderFactoryBean.setSortKeys(sortKey);

        return queryProviderFactoryBean.getObject();

    }

}
