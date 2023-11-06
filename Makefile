DC = docker compose

STORAGE_FILE = docker/storage.yaml
PGADMIN_FILE = docker/pgadmin.yaml

.PHONY: storage
storage:
	${DC} -f ${STORAGE_FILE} up --build -d

.PHONY: drop-storage
drop-storage:
	${DC} -f ${STORAGE_FILE} down

.PHONY: pgadmin4
pgadmin4:
	${DC} -f ${PGADMIN_FILE} up --build -d

.PHONY: drop-pgadmin4
drop-pgadmin4:
	${DC} -f ${PGADMIN_FILE} down

.PHONY: logs-pgadmin4
logs-pgadmin4:
	${DC} -f ${PGADMIN_FILE} logs -f

.PHONY: logs-storage
logs-storage:
	${DC} -f ${STORAGE_FILE} logs -f