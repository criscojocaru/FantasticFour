package com.mps.reserveme.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
@Slf4j
public class FirebaseInitialize {

    @PostConstruct
    public void initialize() {
        try {

            String credentials = "{\n" +
                    "  \"type\": \"service_account\",\n" +
                    "  \"project_id\": \"reserve-me-783b7\",\n" +
                    "  \"private_key_id\": \"01eae6276bd3cdf0f3d353d3c71a727a891d39ca\",\n" +
                    "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCw2I+luNzVczTA\\n8BpENiCzUaESPYtmKkw4u4pFCVPw9QXyCAwNCLSdeXL4tmHDWxdnBt/K32Gf7zFe\\nQfG64574AbUr+x9bHcOD5Jcdm7X/AF4hUPJk9f/BqZa2Wsco1p1SEZ1Ok5hGuM9P\\nZ6Iw+EKNNIU0JBy2XHNk1VNKdwPHdk8dr93Dj3Yua4Jrk26h/m46pvECEM8mUiBM\\nqpqsJJndCNYi2R/qVRy3C6m+k+rFe15UaghC1DT67Tlg1qOlYatnA5aPXokU0gp+\\nQNqFHq9WS8i65lfyeSU7q+F5WXw7WX91rhxlmE1f2vAuAx5vtuw6Me5ZMj0ZmWqH\\nh/MUXxSbAgMBAAECggEAOnnZgfRvPTtjgbrDDLQTOwguaRAltIoe+xF0RqCve3kh\\n1qa3iWXMB1g93qS8DfNkeSJKRNR5fP+ERQUsL96NVbeG1zyp4SCl4kpnd5hh5SiT\\nd5PDGwRG5hw0JCc/PK8+hQEjEXT3gMciccC0Xlnw860esJ+SSPCoDXsGmBRjkQPu\\nY8ie4oeT6gu94SwlX1gJcAEJeKONCoyZIfaDB7IETHpE+Uh1aiBH87Joxa+4vWBj\\nBua4gtbJ3VY6oXzO3l9GGATkLNcEkBqsdLaBaoXPJmo8845uBiz1NT3z18D3ZZ1B\\nWNa6KQ1O8UDA/G4N08etDLLkD/2LY0Z+lgHbZ5/0mQKBgQDi1SXxbYO9luaIN5cn\\nqt7yvXBpoU5yH1GofPCvGc5l2N5ct6rVrJ2HQQtW18HpCPpz62QdqThDLA1jAxQF\\nGomfB8Fbl9pwif1cD0a2dBBAxZy/+v1Clve6J7HveSnQG0sQPgCwMULO1T8V40Oy\\nQU69iwXKdVKe0b9VZm+i3h5F8wKBgQDHlfUCOvPSzFc7M2mGqaiK9Uib/vlz2vjX\\nI1nRsrRg+9AoBL+gawZTDYIm/ZEhCNVsV11+zA9pRMCRZc5JYNEDj9RJoV2CRpgq\\nxQ+gs9Q/10gDE2cAGSH8Zx9Qz9GmCNXnAlPlSBACMaiFBwEBx+ir8rEnoWSXFSUL\\noaFUs4FYuQKBgQDOdrxmmAwFkUicTB0lWf48a/nZ65z/e0pUVgStHIwlX9RmCDdS\\n7h8kcSvlC9XQSv/Ma0FhThmL6pj0umSKkLEbM2/n8rw9GxylorH2eIYirrwScl37\\nMnah1me7VUmdxnn4ercdqHjq0mbtgUKpCZCmLdhDg66EKN5C2U5brFlZyQKBgGQB\\n8RYgAiMDf0ZL2bm/c5PFmo+IMOjrtTrgHNalrBrYsxIKZxbz6eRd5XSyJDGgYoj6\\nMgAiMiq2Z6Ozlq1QFQuq213VvKxVwVPVEKKe2WDv98aKx1bWQSBfb+Knb865qK0r\\njmxaMqNWzLFzs/53dFAMuNY52dSWpMxyPgvaMpsJAoGBAI2XGn30J+/AkaRbaZcS\\n1pB8Io84yOSHWEJJcZdk4/4kaG8B+XWwz75H6k6yaFedgqYZpNCe9WmhYkCD14fb\\nOz6Ve/nF09k8i5EVeG/jHC9xxqoXlEoQ16RByppOa7wjJYWCOOuXPJu9EgARiLai\\n2HTta1RKO0Wk6iHWXwsge47J\\n-----END PRIVATE KEY-----\\n\",\n" +
                    "  \"client_email\": \"firebase-adminsdk-xj1he@reserve-me-783b7.iam.gserviceaccount.com\",\n" +
                    "  \"client_id\": \"108047679741133459125\",\n" +
                    "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n" +
                    "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n" +
                    "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n" +
                    "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-xj1he%40reserve-me-783b7.iam.gserviceaccount.com\"\n" +
                    "}";

            InputStream credentialsStream = new ByteArrayInputStream(credentials.getBytes());

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(credentialsStream))
                    .setDatabaseUrl(Database.FIREBASE_URL.getValue())
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
