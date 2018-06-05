package com.zcsh.epay.db;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.zcsh.epay.action.ConfigLoad;
import com.zcsh.epay.log.LogCvt;

/**
 * 作者：Administrator <br>
 * 创建时间：2018年6月5日 <br>
 * 描述：
 */
public class OracleManager {
	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;

	static {
		InputStream in = null;
		BufferedReader bf = null;
		InputStreamReader isr = null;
		Properties prop = new Properties();
		try {
			in = ConfigLoad.loadAsStream("init.properties");
			isr = new InputStreamReader(in, "utf-8");
			bf = new BufferedReader(isr);
			prop.load(bf);

			String environmemt = prop.getProperty("jdbc");
			in = ConfigLoad.loadAsStream("jdbc-" + environmemt + ".properties");
			isr = new InputStreamReader(in, "utf-8");
			bf = new BufferedReader(isr);
			prop.load(bf);

			reader = Resources.getResourceAsReader("Configuration.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,
					environmemt, prop);
		} catch (Exception e) {
			LogCvt.error(e.getMessage(),e);
		} finally{
			try{
				if(isr!=null) isr.close();
				if(bf!=null) bf.close();
				if(in!=null) in.close();
			}catch(Exception e){}
		}
	}

	public static SqlSessionFactory getSession() {
		return sqlSessionFactory;
	}
}

