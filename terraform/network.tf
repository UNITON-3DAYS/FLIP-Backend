# ============================================================
# GCP API 활성화
# ============================================================
resource "google_project_service" "apis" {
  for_each = toset([
    "compute.googleapis.com",
    "sqladmin.googleapis.com",
    "storage.googleapis.com",
    "vision.googleapis.com",
    "generativelanguage.googleapis.com",
    "iam.googleapis.com",
    "firebase.googleapis.com",
  ])
  service            = each.key
  disable_on_destroy = false
}

# ============================================================
# VPC & 서브넷
# ============================================================
resource "google_compute_network" "main" {
  name                    = "flip-vpc"
  auto_create_subnetworks = false
  depends_on              = [google_project_service.apis]
}

resource "google_compute_subnetwork" "main" {
  name          = "flip-subnet"
  ip_cidr_range = "10.10.0.0/24"
  network       = google_compute_network.main.id
  region        = var.region
}

# ============================================================
# 방화벽
# ============================================================

# SSH
resource "google_compute_firewall" "allow_ssh" {
  name    = "flip-allow-ssh"
  network = google_compute_network.main.name

  allow {
    protocol = "tcp"
    ports    = ["22"]
  }
  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["flip-vm"]
}

# HTTP / HTTPS
resource "google_compute_firewall" "allow_http_https" {
  name    = "flip-allow-http-https"
  network = google_compute_network.main.name

  allow {
    protocol = "tcp"
    ports    = ["80", "443"]
  }
  source_ranges = ["0.0.0.0/0"]
  target_tags   = ["flip-vm"]
}

# 내부 통신
resource "google_compute_firewall" "allow_internal" {
  name    = "flip-allow-internal"
  network = google_compute_network.main.name

  allow { protocol = "tcp" }
  allow { protocol = "udp" }
  allow { protocol = "icmp" }

  source_ranges = ["10.10.0.0/24"]
}
