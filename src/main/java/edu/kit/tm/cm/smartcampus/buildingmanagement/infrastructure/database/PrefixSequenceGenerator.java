package edu.kit.tm.cm.smartcampus.buildingmanagement.infrastructure.database;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class PrefixSequenceGenerator extends SequenceStyleGenerator {

  public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";
  public static final String VALUE_PREFIX_DEFAULT = "";
  private String valuePrefix;

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {

    // add prefix value to the classic SEQUENCED strategy
    return valuePrefix + super.generate(session, object);
  }

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {

    // enable annotation configuration for the wished prefix value
    super.configure(LongType.INSTANCE, params, serviceRegistry);
    valuePrefix =
        ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
  }
}
