# Endpoints

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