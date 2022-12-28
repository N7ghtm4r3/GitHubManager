package com.tecknobit.githubmanager.apps.webhooks.records;

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
    private final int insecureSsl;

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
        super(null);
        this.contentType = contentType;
        this.insecureSsl = insecureSsl;
        this.secret = secret;
        this.url = url;
    }

    /**
     * Constructor to init a {@link Webhook}
     *
     * @param jWebhook : webhook details as {@link JSONObject}
     **/
    public Webhook(JSONObject jWebhook) {
        super(jWebhook);
        contentType = hResponse.getString("content_type");
        insecureSsl = hResponse.getInt("insecure_ssl", 0);
        secret = hResponse.getString("secret");
        url = hResponse.getString("url");
    }

    /**
     * Method to get {@link #contentType} instance <br>
     * Any params required
     *
     * @return {@link #contentType} instance as {@link String}
     **/
    public String getContentType() {
        return contentType;
    }

    /**
     * Method to get {@link #insecureSsl} instance <br>
     * Any params required
     *
     * @return {@link #insecureSsl} instance as int
     **/
    public int getInsecureSsl() {
        return insecureSsl;
    }

    /**
     * Method to get {@link #secret} instance <br>
     * Any params required
     *
     * @return {@link #secret} instance as {@link String}
     **/
    public String getSecret() {
        return secret;
    }

    /**
     * Method to get {@link #url} instance <br>
     * Any params required
     *
     * @return {@link #url} instance as {@link String}
     **/
    public String getUrl() {
        return url;
    }

}
