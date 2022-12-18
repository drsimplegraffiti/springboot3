after bootstrapping the spring boot app fomr the initialzr

delete the all files and folder in the main/java leaving only the java folder
delete help.md
delete static folder in resources
delete template folder in templates
delete com.package_name in the test/java folder
delete the target folder

Then go to the top and and click the drop down and click the edit configuration and delete the springboot application

Then in the src/main/java create the com.abcode
Then create a Main class


```dockerfile
services:
  db:
    container_name: postgres
    image: postgres
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
      PGDATA: /data/postgres
    volumes:
        - db:/data/postgres
    ports:
        - "5432:5432"
    networks:
      - db
    restart: unless-stopped

networks:
  db:
    driver: bridge

volumes:
    db:

```

Then install the docker plugin and then click the two arrows in the docker-compose file


---

Then add the dependencies in the pom.xml

```xml
  <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
```

---

### Create the database 

```
docker exec -it postgres bash

It will open the bash of the postgres container
> root@87bf55b6df9d:/#

Then login using the postgres user
> root@87bf55b6df9d:/# psql -U postgres

```

Then create the database

```sql
CREATE DATABASE customer;
```

Come out of the postgres container

```sql
\q 
```

Then come out of the bash

```sql
exit
```


---

#### Remove all running containers

```bash
docker rm -f $(docker ps -aq)
docker rm $(docker ps -a -q)
```

---

#### Insert data into the database

```sql
INSERT INTO customer (id, name, emaisl, age) VALUES (nextval('customer_id_sequence'), 'John', 'john@gmail.com', 20);
INSERT INTO customer (id, name, email, age) VALUES (nextval('customer_id_sequence'), 'Mary', 'mary@gmail.com', 25);
```