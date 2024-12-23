# Project Akhir Pemprograman Berorientasi Objek
## Nama Kelompok
1. Reisya Nurfaris Dzakwan Alamsyah	2309116102
2. Vincenz Reynard Citro	2309116080
3. Hiskya Harsyal Kila	2309116089
4. Mohammed Noeno Hadianto	2309116081

## Honda

### Deskripsi Program
Website Honda merupakan sebuah website yang berfungsi sebagai katalog dimana admin dapat menambahkan, mengubah dan menghapus data mobil, jadwal test-drive dan user dapat melihat berbagai informasi, gambar dan deskripsi mengenai produk mobil honda. Tidak hanya sebagai katalog saja website ini juga memberikan beberapa fitur untuk memudahkan user seperti fitur simulasi kredit agar user dapat mengetahui besaran bunga, penjadwalan test-drive agar memudahkan user untuk dapat melakukan test-drive mobil honda dan fitur yang tidak kalah penting yaitu fitur servis fee agar user mengetahui besaran harga maintenence mobil honda.

Fitur Aplikasi 
- Login
- Menu edit produk
- Menu Awal
- Simulasi Kredit
- Daftar test-drive
- Servis fee

### Flowchart
![honda_armata](https://github.com/user-attachments/assets/d7ac195c-cf55-4cce-987e-86ec79dc6e49)

### ERD
- Logical
  ![WhatsApp Image 2024-11-21 at 22 59 08_cbe0f339](https://github.com/user-attachments/assets/495361f0-4ae7-402b-8260-b61d43c71bcd)

- Relational
  ![WhatsApp Image 2024-11-21 at 22 59 31_7279c860](https://github.com/user-attachments/assets/b15d9e98-6444-430a-8730-67e0c2546e06)

### Activity Diagram
![pa_honda 1](https://github.com/user-attachments/assets/dbe8caec-b639-47b1-bbe9-cf52c085139b)

### Use Case Diagram
![WhatsApp Image 2024-11-21 at 17 30 08_8ed3924c](https://github.com/user-attachments/assets/d5440a3d-5566-4be2-b046-44d547af54c8)

### Struktur Project
1. Package com.hondaamartha
App: Entry point dari aplikasi (main class).
CarDetailsController, CarsController, HomeController, dll.: Kelas-kelas controller untuk mengelola logika dan interaksi antar bagian aplikasi.
Database: Kemungkinan berisi logika atau koneksi ke database.
NavbarController: Mengatur navigasi di dalam aplikasi.
2. Package com.hondaamartha.car
Category, Hatchback, SUV, Sedan: Representasi berbagai kategori mobil, kemungkinan model untuk data mobil.
3. Package com.hondaamartha.model
Berisi entitas utama:
Admin, Member, Mobil, TestDrive, dll.: Representasi data untuk fitur seperti pengguna, mobil, test drive, dll.
CreditSimulation, ServiceFee: Terkait perhitungan atau fitur keuangan.
4. Folder src/main/resources
Folder images: Berisi gambar-gambar yang digunakan dalam aplikasi.
File .fxml: File antarmuka pengguna yang digunakan oleh JavaFX untuk mendesain tampilan GUI.
styles.css: Berisi styling untuk antarmuka aplikasi.

![WhatsApp Image 2024-11-21 at 23 05 24_11aea7b7](https://github.com/user-attachments/assets/e94c6457-21d0-42ce-9777-1af73a262fa7)

![WhatsApp Image 2024-11-21 at 23 05 25_2ae8636a](https://github.com/user-attachments/assets/76a7028b-1092-4a54-98fa-1f6d38854cef)

### Cara Pengguanaan

USER 

Login user : user dapat melakukan login dengan memasukan username dan password yang telah ada 
![WhatsApp Image 2024-11-21 at 20 11 47_28552ac4](https://github.com/user-attachments/assets/a94e14fa-8df0-4e71-8ea5-91fbff357b63)

Setelah berhasil login maka user akan masuk ke tampilan awal yaitu menu beranda 
![WhatsApp Image 2024-11-21 at 20 13 59_6fa59ec7](https://github.com/user-attachments/assets/fbf353fc-a9c5-49a4-9b2c-8b4fca5fc740)

User dapat melihat berbagai produk dengan mengakses menu awal dengan menekan tombol produk
![WhatsApp Image 2024-11-21 at 22 14 39_fc3daf8a](https://github.com/user-attachments/assets/c9206bd7-669a-4751-ab88-30ff5e6cab0f)

User dapat melihat deskripsi produk dengan menekan foto produk 
![WhatsApp Image 2024-11-21 at 22 15 31_c762da11](https://github.com/user-attachments/assets/4f8fa8ac-64a0-4757-a497-242acb975cf8)

User dapat mengakses menu mobil yang terdapat simulasi kredit, test drive, dan servis fee
![WhatsApp Image 2024-11-21 at 20 37 33_1c9042a4](https://github.com/user-attachments/assets/cdd6c30b-fc98-48fa-a50c-3813655ff62c)

Tampilan simulasi kredit
![WhatsApp Image 2024-11-21 at 20 39 26_7e9fd6fe](https://github.com/user-attachments/assets/d300033b-5e0c-4b57-850f-232fced55b50)

Tampilan servis fee 
![WhatsApp Image 2024-11-21 at 20 44 10_d0550e47](https://github.com/user-attachments/assets/e708992a-bc14-48f9-aa19-21decc6146f0)

User juga dapat menambahkan data jadwal test drive 
![WhatsApp Image 2024-11-21 at 20 57 46_aca1bc4d](https://github.com/user-attachments/assets/5dbeefaf-dc92-4a9d-b044-646861f18881)

Tampilan jadwal berhasil ditambahkan
![WhatsApp Image 2024-11-21 at 20 59 33_5279146c](https://github.com/user-attachments/assets/0aec3311-2553-43bd-889a-ad2f6681418b)

ADMIN

Login admin : admin dapat melakukan login dengan memasukan username dan password yang telah ada 
![WhatsApp Image 2024-11-21 at 20 11 47_28552ac4](https://github.com/user-attachments/assets/a94e14fa-8df0-4e71-8ea5-91fbff357b63)

Setelah berhasil login maka admin akan masuk ke tampilan awal yaitu menu beranda 
![WhatsApp Image 2024-11-21 at 20 13 59_6fa59ec7](https://github.com/user-attachments/assets/fbf353fc-a9c5-49a4-9b2c-8b4fca5fc740)

Agar dapat melakukan crud admin perlu untuk mengakses menu awal dengan menekan tombol produk
![WhatsApp Image 2024-11-21 at 23 08 08_7f6398da](https://github.com/user-attachments/assets/8ff29319-579d-4850-b0ce-8e4fe0b9247a)

Admin dapat menambahkan, mengubah dan menghapus data mobil melalui manage tipe mobil 
![WhatsApp Image 2024-11-21 at 20 35 54_fa29f35e](https://github.com/user-attachments/assets/f9ab6d6d-deea-4c6c-adf1-57ba523fc844)

Menambah data mobil
![WhatsApp Image 2024-11-21 at 20 48 26_b71eb977](https://github.com/user-attachments/assets/f5a80fed-cdf0-4603-8e2f-e757e1fc8315)

Mengubah data mobil
![WhatsApp Image 2024-11-21 at 20 51 02_ef783ec5](https://github.com/user-attachments/assets/a18a26c0-f3cb-4bf7-bde4-b818ae68f4cc)

Menghapus data mobil
![WhatsApp Image 2024-11-21 at 20 52 13_79f0539b](https://github.com/user-attachments/assets/b4dc8d82-e8d5-4515-b095-d92a3533863c)

Tampilan data mobil berhasil terhapus 
![WhatsApp Image 2024-11-21 at 20 53 01_559c270c](https://github.com/user-attachments/assets/9ab675d8-1e7b-437f-96fd-b91d0a14deb5)

Admin dapat mengakses menu mobil yang terdapat simulasi kredit, test drive, dan servis fee
![WhatsApp Image 2024-11-21 at 20 37 33_1c9042a4](https://github.com/user-attachments/assets/cdd6c30b-fc98-48fa-a50c-3813655ff62c)

Tampilan simulasi kredit
![WhatsApp Image 2024-11-21 at 20 39 26_7e9fd6fe](https://github.com/user-attachments/assets/d300033b-5e0c-4b57-850f-232fced55b50)

Tampilan servis fee 
![WhatsApp Image 2024-11-21 at 20 44 10_d0550e47](https://github.com/user-attachments/assets/e708992a-bc14-48f9-aa19-21decc6146f0)

Admin dapat menambahkan data jadwal test drive 
![WhatsApp Image 2024-11-21 at 20 57 46_aca1bc4d](https://github.com/user-attachments/assets/5dbeefaf-dc92-4a9d-b044-646861f18881)

Tampilan jadwal berhasil ditambahkan
![WhatsApp Image 2024-11-21 at 20 59 33_5279146c](https://github.com/user-attachments/assets/0aec3311-2553-43bd-889a-ad2f6681418b)

Admin juga dapat memanajemen jadwal tesdrive 
![WhatsApp Image 2024-11-21 at 21 00 40_7a69757d](https://github.com/user-attachments/assets/69c3f16d-bdf6-46ab-8ae4-947c08ea6ff0)
