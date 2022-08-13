package edu.kit.tm.cm.smartcampus.buildingmanagement.api.configuration.converter;

import com.nimbusds.jose.shaded.json.JSONArray;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * This class represents an implementation of {@link Converter} for Keycloak Access Tokens, it
 * extracts granted authorities from the {@link Jwt} token and converts then to {@link
 * GrantedAuthority} objects.
 *
 * @author Bastian Bacher, Dennis Fadeev
 */
public class KeyCloakGrantedAuthoritiesConverter
    implements Converter<Jwt, Collection<GrantedAuthority>> {

  @Override
  public Collection<GrantedAuthority> convert(@NonNull Jwt jwt) {
    return getScopes(jwt).stream()
        .map(String::toUpperCase)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  private Collection<String> getScopes(Jwt jwt) {
    Collection<String> result = new ArrayList<>();
    JSONArray groups = (JSONArray) jwt.getClaims().get("groups");
    if (Objects.isNull(groups)) return Collections.emptyList();
    for (Object group : groups) result.add((String) group);
    return result;
  }
}
