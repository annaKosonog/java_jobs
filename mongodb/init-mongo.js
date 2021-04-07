
print('Start ##########################################################');
db = db.getSiblingDB('offers');
db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [{role:"readWrite", db: "offers"}]
    }
);

print('END #################################################################');

