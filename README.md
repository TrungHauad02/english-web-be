
## Build Image sử dụng Dockerfile
```bash
docker build -t <tên image>:<version> .  
# Ví dụ
docker build -t study-english-web:v1 .
```

---

#### Xem danh sách các image có trên máy
```bash
docker image ls
```

---

#### Chạy Image
```bash
docker container run -d -p <port>:<port> <tên image>:<version>  
# Ví dụ
docker container run -d -p 8080:8080 study-english-web:v1
```

---

#### Xem danh sách các container đang chạy
```bash
docker container ls
```

---

#### Dừng Container đang chạy
```bash
docker container stop <tên container>
```

---

#### Xem danh sách các container đang chạy hoặc đã dừng
```bash
docker ps -a
```

---

#### Xóa Container khỏi máy
```bash
docker rm <id container>
```

---

#### Xem danh sách Image có trên máy
```bash
docker image ls
```

---

#### Xóa Image khỏi máy
```bash
docker rmi <Repository>:<tag>
```

---

## Chạy MySQL trên Docker
```bash
docker run --detach --env MYSQL_ROOT_PASSWORD=123456 --env MYSQL_USER=study-english --env MYSQL_PASSWORD=123456 --env MYSQL_DATABASE=study-english --name mysql --publish 3307:3306 mysql:8-oracle
```

## Kết nối với mysql docker bằng mysqlsh
```bash
mysqlsh
\connect study-english@localhost:3307
```
```bash
\use study-english
select * from topic;
```