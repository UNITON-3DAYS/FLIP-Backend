 # ============================================================
# Cloud SQL — MySQL 8.0, db-f1-micro, public IP
# ============================================================
resource "google_sql_database_instance" "main" {
  name             = "flip-mysql"
  database_version = "MYSQL_8_0"
  region           = var.region

  settings {
    tier = "db-f1-micro"

    ip_configuration {
      ipv4_enabled = true
      authorized_networks {
        name  = "prod-vm"
        value = google_compute_address.prod.address
      }
      authorized_networks {
        name  = "local-dev"
        value = "211.181.188.123/32"
      }
    }

    backup_configuration {
      enabled            = true
      binary_log_enabled = true
    }
  }

  deletion_protection = true

  depends_on = [google_project_service.apis]
}

resource "google_sql_database" "app" {
  name     = "flip"
  instance = google_sql_database_instance.main.name
}

resource "google_sql_user" "app" {
  name     = var.db_user
  instance = google_sql_database_instance.main.name
  password = var.db_password
}
