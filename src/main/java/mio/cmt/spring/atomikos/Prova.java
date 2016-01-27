package mio.cmt.spring.atomikos;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Prova {
	
	
	private JdbcTemplate mioJdbcTemplate;
	private JdbcTemplate crmJdbcTemplate;

	@Autowired
	@Qualifier("crmdataSource")
    public void setCrmDataSource(DataSource dataSource) {
        this.crmJdbcTemplate = new JdbcTemplate(dataSource);
    }
	

	@Autowired
	@Qualifier("miodataSource")
    public void setMioDataSource(DataSource dataSource) {
        this.mioJdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	public void insertMio() throws Exception{
		mioJdbcTemplate.update("insert into acidrest (rest,temp) values (?,?)",1,1);
	}
	
	public void insertCrm() throws Exception{
		crmJdbcTemplate.update("insert into canale (id) values (?)",1);
	}
	
	
	
	public static void main(String[] args) throws Exception{
		ApplicationContext c = new ClassPathXmlApplicationContext("applicationContext.xml");
		Prova p = c.getBean(Prova.class);
		p.insertCrm();
		p.insertMio();
		
		
	}
	
	//prima
	//su feature1
	//feature2
	//feature3
}
