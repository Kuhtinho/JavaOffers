db.createUser(
    {
        user: "kuchta",
        pwd: "kuchta",
        roles: [
            {
                role: "readWrite",
                db: "offers"
            }
        ]
    }
)