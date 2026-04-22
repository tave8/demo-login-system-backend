# Endpoints

## /auth

### POST /login

Request

```
email: str
password: str
```

Response 

```
accessToken: str
```

### POST /register

Request 

```
email: str
password: str
firstname: str
lastname: str
```

Response

```
userId: str
```


## /users


### GET /me

Response

```
userId: str
email: str 
firstname: str
lastname: str
avatarUrl: str
createdAt: timestamp
```


### PUT /me


Request

```
firstname: str
lastname: str
```

Response

```
userId: str
email: str 
firstname: str
lastname: str
avatarUrl: str
createdAt: timestamp
```

## /articles


### GET /me


Response

```
content = [
    {
        articleId: str
        title: str
        content: str
        coverUrl: str
        createdAt: timestamp
    }
]
```



### GET /:articleId

Response 

```
articleId: str
title: str
content: str
coverUrl: str
createdAt: timestamp
```

### POST /

Request

```
title: str
content: str
```

Response 

```
articleId: str
title: str
content: str
coverUrl: str
createdAt: timestamp
```


### PUT /:articleId

Request

```
title: str
content: str
```

Response

```
articleId: str
title: str
content: str
coverUrl: str
createdAt: timestamp
```

### DELETE /:articleId



Response

```
no response
```



# Endpoints - overview

```

/auth
    POST /login      (login)
    POST /register  (create new profile/account)

/users
    GET /me        (get my profile)
    PUT /me         (update my profile)
    
/articles
    GET /me                (get my articles)
    GET /:articleId        (get article, requires that it's my article)
    POST /                  (add article as my profile)
    PUT /:articleId         (updates an article, requires that it's my article)
    DELETE /:articleId       (deletes an article, requires that it's my article)
    
```






# Entities

```

User
    userId
    email
    password
    firstName
    lastName
    avatarUrl
    createdAt
    

Article
    articleId
    userId
    title
    content
    coverUrl
    createdAt

```


# Entity cardinality

```

1 user --has--> N articles

1 article --written by--> 1 user

```


# Configuration

Run/Edit configuration. Spring must be in "local" profile when in local environment?