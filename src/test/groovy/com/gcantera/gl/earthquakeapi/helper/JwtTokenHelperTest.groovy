package com.gcantera.gl.earthquakeapi.helper

import spock.lang.Specification

class JwtTokenHelperTest extends Specification {
    JwtTokenHelper jwtTokenHelper = new JwtTokenHelper()

    def setup() {
        jwtTokenHelper.setSecret("testsecret")
        jwtTokenHelper.setValidity(6000)
    }

    def "test buildToken"() {
        given:
        String expectedToken

        when:
        expectedToken = jwtTokenHelper.buildToken("test")

        then:
        expectedToken.startsWith("Bearer ")
    }
}
