Restore:
cat your_dump.sql | docker exec -i your-db-container psql -U postgres
