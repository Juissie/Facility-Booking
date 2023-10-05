package com.example.cs203g1t3.security.jwt;

import com.example.cs203g1t3.models.User;
import com.example.cs203g1t3.services.CustomUserDetailService;
import com.example.cs203g1t3.services.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgHibernateConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import java.security.Key;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
//    @Value("${facilityBooking.app.jwtSecret}")
    private final String jwtSecret = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";

    private final long jwtExpirationMs = 60000L;

//    public String generateJwtToken(Authentication authentication) {
//
//        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
//
//        return Jwts.builder()
//                .setSubject((userPrincipal.getUsername()))
//                .setIssuedAt(new Date())
//                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//                .signWith(key(), SignatureAlgorithm.HS256)
//                .compact();
//    }

    public String generateJwtToken(CustomUserDetails userPrincipal) {
        return generateTokenFromUsername(userPrincipal.getUsername());
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(key(),SignatureAlgorithm.HS256)
                .compact();
    }

//    public String getUserNameFromJwtToken(String token) {
//        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(gmail\\.com|yahoo\\.com)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isValidPassword(String password) {
        // Check if the password is at least 8 characters long
        if (password.length() < 8) {
            return false;
        }

        // Check for at least one uppercase letter
        Pattern uppercasePattern = Pattern.compile("[A-Z]");
        Matcher uppercaseMatcher = uppercasePattern.matcher(password);

        if (!uppercaseMatcher.find()) {
            return false;
        }

        // Check for at least one lowercase letter
        Pattern lowercasePattern = Pattern.compile("[a-z]");
        Matcher lowercaseMatcher = lowercasePattern.matcher(password);

        if (!lowercaseMatcher.find()) {
            return false;
        }

        // Check for at least one digit (number)
        Pattern digitPattern = Pattern.compile("[0-9]");
        Matcher digitMatcher = digitPattern.matcher(password);

        if (!digitMatcher.find()) {
            return false;
        }

        // Check for at least one special character
        Pattern specialCharacterPattern = Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]");
        Matcher specialCharacterMatcher = specialCharacterPattern.matcher(password);

        if (!specialCharacterMatcher.find()) {
            return false;
        }

        // If all checks pass, the password is valid
        return true;
    }

    public boolean isValidNric(String nric) {
        // Check if the NRIC is in the correct format
        Pattern pattern = Pattern.compile("^[STFG]\\d{7}[A-Z]$");
        Matcher matcher = pattern.matcher(nric);

        if (!matcher.matches()) {
            return false;
        }

        // Extract the first character (S/T/F/G)
        char firstChar = nric.charAt(0);

        // Extract the numeric part of the NRIC
        int numericPart = Integer.parseInt(nric.substring(1, 8));

        // Define the checksum letters for the first character
        char[] checksumLetters = "JZIHGFEDCBA".toCharArray();

        // Calculate the checksum based on the first character
        int checksum = (firstChar == 'T' || firstChar == 'G') ? 4 : 0;
        checksum += (numericPart % 10) * 2;

        // Check if the NRIC is valid by comparing the calculated checksum with the actual checksum letter
        return nric.charAt(8) == checksumLetters[checksum % 11];
    }


}
