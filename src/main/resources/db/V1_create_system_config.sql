INSERT INTO app_config
(config_key, config_value, description, updated_at, updated_by)
VALUES
    (
        'URL_DEFAULT_EXPIRY_DAYS',
        '30',
        'Default number of days before a newly created short URL expires',
        CURRENT_TIMESTAMP,
        NULL
    ),
    (
        'MAX_URL_LENGTH',
        '2048',
        'Maximum allowed length of the original URL',
        CURRENT_TIMESTAMP,
        NULL
    ),
    (
        'SHORT_CODE_LENGTH',
        '7',
        'Default length of generated short URL codes',
        CURRENT_TIMESTAMP,
        NULL
    ),
    (
        'MAX_ACTIVE_URLS_PER_USER',
        '10',
        'Maximum number of active short URLs a user can have by default',
        CURRENT_TIMESTAMP,
        NULL
    ),
    (
        'DEFAULT_URL_SLOTS',
        '5',
        'Number of URL slots assigned to a newly registered user',
        CURRENT_TIMESTAMP,
        NULL
    ),
    (
        'MIN_PASSWORD_LENGTH',
        '8',
        'Minimum password length required during registration',
        CURRENT_TIMESTAMP,
        NULL
    ),
    (
        'MAX_LOGIN_ATTEMPTS',
        '5',
        'Maximum allowed failed login attempts before temporary restriction',
        CURRENT_TIMESTAMP,
        NULL
    );