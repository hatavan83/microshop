#!/bin/bash

mongo <<EOF
    var cfg = {
        "_id": "rs",
        "version": 1,
        "members": [
            {
                "_id": 0,
                "host": "mongo-0.mongo:27017",
                "priority": 2
            },
            {
                "_id": 1,
                "host": "mongo-1.mongo:27017",
                "priority": 0
            }
        ]
    };
    rs.initiate(cfg, { force: true });
    rs.status();
EOF
sleep 10

mongo <<EOF
    use admin;
    admin = db.getSiblingDB("admin");
    admin.createUser({
	    user: "root",
        pwd: "root",
        roles: [ { role: "root", db: "admin" } ]
    });
    db.getSiblingDB("admin").auth("root", "root");
    rs.status();
EOF