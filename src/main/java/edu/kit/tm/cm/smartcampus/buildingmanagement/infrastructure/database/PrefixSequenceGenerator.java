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

/**
 * Custom prefix sequence generator for database primary key generation. It generates a prefixed
 * sequence from {@link SequenceStyleGenerator}. The prefix can now be configured by the {@link
 * org.hibernate.annotations.GenericGenerator} annotation.
 */
public class PrefixSequenceGenerator extends SequenceStyleGenerator {

  // prefix value string parameter
  public static final String VALUE_PREFIX_PARAMETER = "valuePrefix";

  // default prefix parameter
  public static final String VALUE_PREFIX_DEFAULT = "";

  // prefix value can be custom or default
  private String valuePrefix;

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object object)
      throws HibernateException {

    // call prefixed super generate with classic SEQUENCE strategy
    return valuePrefix + super.generate(session, object);
  }

  @Override
  public void configure(Type type, Properties params, ServiceRegistry serviceRegistry)
      throws MappingException {

    // enable configure annotation for value prefix
    super.configure(LongType.INSTANCE, params, serviceRegistry);
    valuePrefix =
        ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, params, VALUE_PREFIX_DEFAULT);
  }
}
