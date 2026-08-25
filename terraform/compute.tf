# ============================================================
# 고정 외부 IP
# ============================================================
resource "google_compute_address" "prod" {
  name   = "flip-prod-ip"
  region = var.region
}

# ============================================================
# prod VM — e2-medium (2 vCPU 공유, 4GB)
# Spring Boot(API) + Python(AI/VLM) 컨테이너를 docker compose로 한 VM에서 실행
# dev/prod 환경 구분 없음, 단일 인스턴스
# ============================================================
resource "google_compute_instance" "prod" {
  name                      = "flip-prod"
  machine_type              = "e2-medium"
  zone                      = var.zone
  tags                      = ["flip-vm"]
  allow_stopping_for_update = true

  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2204-lts"
      size  = 20
      type  = "pd-ssd"
    }
  }

  network_interface {
    subnetwork = google_compute_subnetwork.main.id
    access_config {
      nat_ip = google_compute_address.prod.address
    }
  }

  service_account {
    email  = google_service_account.app.email
    scopes = ["cloud-platform"]
  }

  metadata = {
    ssh-keys = "${var.ssh_username}:${var.ssh_public_key}"
  }

  depends_on = [google_project_service.apis]
}
