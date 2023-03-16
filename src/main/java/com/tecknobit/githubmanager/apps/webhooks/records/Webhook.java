package com.tecknobit.githubmanager.apps.webhooks.records;

import com.tecknobit.apimanager.annotations.Returner;
import com.tecknobit.githubmanager.GitHubManager.ReturnFormat;
import com.tecknobit.githubmanager.records.parents.GitHubResponse;
import org.json.JSONObject;

/**
 * The {@code Webhook} class is useful to format a GitHub's webhook
 *
 * @author N7ghtm4r3 - Tecknobit
 * @apiNote see the official documentation at:
 * <ul>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/webhooks#get-a-webhook-configuration-for-an-app">
 *             Get a webhook configuration for an app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/apps/webhooks#update-a-webhook-configuration-for-an-app">
 *             Update a webhook configuration for an app</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/webhooks#get-a-webhook-configuration-for-an-organization">
 *             Get a webhook configuration for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/orgs/webhooks#update-a-webhook-configuration-for-an-organization">
 *             Update a webhook configuration for an organization</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/webhooks/repo-config#get-a-webhook-configuration-for-a-repository">
 *             Get a webhook configuration for a repository</a>
 *     </li>
 *     <li>
 *         <a href="https://docs.github.com/en/rest/webhooks/repo-config#update-a-webhook-configuration-for-a-repository">
 *             Update a webhook configuration for a repository</a>
 *     </li>
 * </ul>
 * @see GitHubResponse
 **/
public class Webhook extends GitHubResponse {

    /**
     * {@code contentType} the media type used to serialize the payloads. Supported values include {@code "json"} and
     * {@code "form"}. The default is {@code "form"}.
     **/
    private final String contentType;

    /**
     * {@code insecureSsl} determines whether the SSL certificate of the host for {@code "url"} will be verified
     * when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     * (verification is not performed). The default is {@code "0"}.
     * <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to man-in-the-middle and other attacks </strong>
     **/
    private final Object insecureSsl;

    /**
     * {@code secret} if provided, the {code "secret"} will be used as the {@code "key"} to generate the HMAC hex digest
     * value for <a href="https://docs.github.com/webhooks/event-payloads/#delivery-headers">delivery signature headers</a>
     **/
    private final String secret;

    /**
     * {@code url} the URL to which the payloads will be delivered
     **/
    private final String url;

    /**
     * {@code username} the username of the webhook
     **/
    private final String username;

    /**
     * {@code password} the password of the webhook
     **/
    private final String password;

    /**
     * Constructor to init a {@link Webhook}
     *
     * @param contentType : the media type used to serialize the payloads. Supported values include {@code "json"} and
     *                    {@code "form"}. The default is {@code "form"}
     * @param insecureSsl : determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                    when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                    (verification is not performed). The default is {@code "0"}.
     *                    <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to man-in-the-middle and other attacks </strong>
     * @param secret      : if provided, the {code "secret"} will be used as the {@code "key"} to generate the HMAC hex digest
     *                    value for <a href="https://docs.github.com/webhooks/event-payloads/#delivery-headers">delivery signature headers</a>
     * @param url         :the URL to which the payloads will be delivered
     **/
    public Webhook(String contentType, int insecureSsl, String secret, String url) {
        this(contentType, insecureSsl, secret, url, null, null);
    }

    /**
     * Constructor to init a {@link Webhook}
     *
     * @param contentType : the media type used to serialize the payloads. Supported values include {@code "json"} and
     *                    {@code "form"}. The default is {@code "form"}
     * @param insecureSsl : determines whether the SSL certificate of the host for {@code "url"} will be verified
     *                    when delivering payloads. Supported values include {@code "0"} (verification is performed) and {@code "1"}
     *                    (verification is not performed). The default is {@code "0"}.
     *                    <strong>Strongly recommendation not setting this to {@code "1"} as you are subject to man-in-the-middle and other attacks </strong>
     * @param secret      : if provided, the {code "secret"} will be used as the {@code "key"} to generate the HMAC hex digest
     *                    value for <a href="https://docs.github.com/webhooks/event-payloads/#delivery-headers">delivery signature headers</a>
     * @param url         :the URL to which the payloads will be delivered
     * @param username:   the username of the webhook
     * @param password:   the password of the webhook
     **/
    public Webhook(String contentType, int insecureSsl, String secret, String url, String username, String password) {
        super(null);
        this.contentType = contentType;
        this.insecureSsl = insecureSsl;
        this.secret = secret;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor to init a {@link Webhook}
     *
     * @param jWebhook : webhook details as {@link JSONObject}
     **/
    public Webhook(JSONObject jWebhook) {
        super(jWebhook);
        contentType = hResponse.getString("content_type");
        insecureSsl = hResponse.get("insecure_ssl");
        secret = hResponse.getString("secret");
        url = hResponse.getString("url");
        username = hResponse.getString("username");
        password = hResponse.getString("password");
    }

    /**
     * Method to get {@link #contentType} instance <br>
     * No-any params required
     *
     * @return {@link #contentType} instance as {@link String}
     **/
    public String getContentType() {
        return contentType;
    }

    /**
     * Method to get {@link #insecureSsl} instance <br>
     * No-any params required
     *
     * @return {@link #insecureSsl} instance as int
     **/
    public <T> T getInsecureSsl() {
        return (T) insecureSsl;
    }

    /**
     * Method to get {@link #secret} instance <br>
     * No-any params required
     *
     * @return {@link #secret} instance as {@link String}
     **/
    public String getSecret() {
        return secret;
    }

    /**
     * Method to get {@link #url} instance <br>
     * No-any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

    /**
     * Method to get {@link #username} instance <br>
     * No-any params required
     *
     * @return {@link #username} instance as {@link String}
     **/
    public String getUsername() {
        return username;
    }

    /**
     * Method to get {@link #password} instance <br>
     * No-any params required
     *
     * @return {@link #password} instance as {@link String}
     **/
    public String getPassword() {
        return password;
    }

    /**
     * Method to create a webhook
     *
     * @param webhookResponse: obtained from GitHub's response
     * @param format:          return type formatter -> {@link ReturnFormat}
     * @return webhook as {@code "format"} defines
     **/
    @Returner
    public static <T> T returnWebhook(String webhookResponse, ReturnFormat format) {
        switch (format) {
            case JSON:
                return (T) new JSONObject(webhookResponse);
            case LIBRARY_OBJECT:
                return (T) new Webhook(new JSONObject(webhookResponse));
            default:
                return (T) webhookResponse;
        }
    }

}
