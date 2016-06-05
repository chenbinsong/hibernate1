package com.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import entity.Student;

public class StudentTest {

	@Test
	public void createTable()
	{
		Configuration configuration=new Configuration().configure();
		SchemaExport schemaExport=new SchemaExport(configuration);
		schemaExport.create(true, true);
	}
	
	@Test
	public void add()
	{
		Configuration configuration=new Configuration().configure();
		
		ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		SessionFactory sessionFactory=configuration.buildSessionFactory(serviceRegistry);
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Student student=new Student();
		student.setName("ÕÅÈý");
		student.setAge(11);
		
		try {
			session.save(student);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
		}
	}
}
