package com.library.model.client.response;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class JwtAuthenticationResponse {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public JwtAuthenticationResponse setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JwtAuthenticationResponse that = (JwtAuthenticationResponse) o;

        return new EqualsBuilder()
                .append(accessToken, that.accessToken)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(accessToken)
                .toHashCode();
    }
}
