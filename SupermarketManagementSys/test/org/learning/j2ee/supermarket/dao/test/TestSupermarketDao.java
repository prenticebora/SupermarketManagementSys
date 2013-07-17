/**
 * 
 */
package org.learning.j2ee.supermarket.dao.test;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.learning.j2ee.supermarket.dao.SupermarketMySql;
import org.learning.j2ee.supermarket.dao.Basicmessage;
import org.learning.j2ee.supermarket.dao.BasicmessageDao;

/**
 * @author brliu
 * 
 */
public class TestSupermarketDao {
	private Connection conn;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		conn = SupermarketMySql.getConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testBasicMessageDao() {
		Basicmessage basicmessage = new Basicmessage();

		basicmessage.setName("Brian");
		basicmessage.setAge(32);
		basicmessage.setDept(2);
		basicmessage.setPositionId(5);

		BasicmessageDao basicmessageDao = new BasicmessageDao();

		try {
			basicmessageDao.create(conn, basicmessage);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL error encountered with message: "
					+ e.toString());
			
			
			fail();
			
		} finally {
			try {
				conn.close();
			} catch (SQLException ignore) {
				System.out
						.println("SQL error encountered during connection closure. Ignore...");
			}
		}
		
	}

}
