package org.project.shoestoreproject.Logout;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ListToken {
    private Set<String> listToken = new HashSet<>();

    public void addToken(String token) {
        listToken.add(token);
    }

    public boolean isListToken(String token) {
        return listToken.contains(token);
    }
}
