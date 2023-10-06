package wms.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wms.domain.category.entity.ContractType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class DemoJob {
    @Autowired
    public NamedParameterJdbcTemplate postgresJDBCTemplate;
    @Autowired
    public NamedParameterJdbcTemplate mysqlJDBCTemplate;
//    @Scheduled(fixedDelay = 10000)
    public void demoDataSource() {
        String query = "select * from scm_contract_type";
        Map<String, Object> params = new HashMap<>();
        List<ContractType> lstContracts = postgresJDBCTemplate.query(query, params, (resultSet, rowNum) -> ContractType.builder()
                .code(resultSet.getString("code"))
                .name(resultSet.getString("name"))
                .build()
        );
        for (ContractType contract : lstContracts) {
            log.info("Value: {} {} {}", contract.getName(), contract.getCode(), contract.getDeleted());
        }
    }
//    @Scheduled(fixedDelay = 10000)
    public void demoDataSource2() {
        String query = "select * from scm_contract_type";
        Map<String, Object> params = new HashMap<>();
        List<ContractType> lstContracts = mysqlJDBCTemplate.query(query, params, (resultSet, rowNum) -> ContractType.builder()
                .code(resultSet.getString("code"))
                .name(resultSet.getString("name"))
                .build()
        );
        for (ContractType contract : lstContracts) {
            log.info("Value 2: {} {} {}", contract.getName(), contract.getCode(), contract.getDeleted());
        }
    }
}


