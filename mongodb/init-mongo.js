
print('Start ##########################################################');

db = db.getSiblingDB('offer');
db.createUser(
    {
        user: "root",
        pwd: "root",
        roles: [{role:"readWrite", db: "offer"}]
    }
);

print('END #################################################################');

