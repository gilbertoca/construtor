/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gilbertoca.gfi.util;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author gilberto
 */
                        

public class ORMHelper {
   private static final ThreadLocal tls = new ThreadLocal();
   private static final ThreadLocal tltx = new ThreadLocal();
   private static EntityManagerFactory sf;

   protected static synchronized 
   EntityManagerFactory getSessionFactory(String name) { 
      if (sf == null) {
         sf = Persistence.createEntityManagerFactory(name);
      }
      return sf;
   }
 public static void openSession() {
      EntityManager s = (EntityManager) tls.get();
      if (s == null) {
         s = getSessionFactory("test").createEntityManager();
         tls.set(s);
      }
   }  

   public static EntityManager getCurrentSession() {
      return (EntityManager) tls.get();
   }

   public static void closeSession() {
      EntityManager s = (EntityManager) tls.get();
      tls.set(null);
      if (s != null && s.isOpen()) s.close();
   }
  public static void beginTransaction() {
      EntityTransaction tx = (EntityTransaction) tltx.get();
      if (tx == null) {
         tx = getCurrentSession().getTransaction();
         tx.begin();
         tltx.set(tx);
      }
   }

   public static void commitTransaction() {
      EntityTransaction tx = (EntityTransaction)tltx.get();
      if (tx != null && tx.isActive()) tx.commit();
      tltx.set(null);
   }

   public static void rollbackTransaction() {
      EntityTransaction tx = (EntityTransaction)tltx.get();
      tltx.set(null);
      if (tx != null && tx.isActive()) tx.rollback(); 
   } 
  public static void create( Serializable obj ) {
      getCurrentSession().persist((Object) obj);		    
   }

   public static Object retrieve(Class clz, Serializable key) {
      return getCurrentSession().find( clz, (Object) key );
   }
	
   public static Object update( Serializable obj ) {
      return getCurrentSession().merge((Object) obj);
   } 

   public static void delete( Serializable obj ) {
      getCurrentSession().remove( (Object) obj);
   }    
}