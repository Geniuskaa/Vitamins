package com.example.project_vita;

<!-- PATH: src\main\java\com\example\project_vita\MainActivity.java -->

        final int CAMERA_REQUEST = 1;
        final int PIC_CROP = 2;
        private Uri picUri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_main);
            setTitle("Съёмка и Кадрирование");
        }

        public void onClick(View v) {
            try {
                // Намерение для запуска камеры
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(captureIntent, CAMERA_REQUEST);
            } catch (ActivityNotFoundException e) {
                // Выводим сообщение об ошибке
                String errorMessage = "Ваше устройство не поддерживает съемку";
                Toast toast = Toast
                        .makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK) {
                // Вернулись от приложения Камера
                if (requestCode == CAMERA_REQUEST) {
                    // Получим Uri снимка
                    picUri = data.getData();
                    // кадрируем его
                    performCrop();
                }
                // Вернулись из операции кадрирования
                else if(requestCode == PIC_CROP){
                    Bundle extras = data.getExtras();
                    // Получим кадрированное изображение
                    Bitmap thePic = extras.getParcelable("data");
                    // передаём его в ImageView
                    ImageView picView = (ImageView)findViewById(R.id.picture);
                    picView.setImageBitmap(thePic);
                }
            }
        }

        private void performCrop(){
            try {
                // Намерение для кадрирования. Не для всех
                Intent cropIntent = new Intent("com.android.camera.action.CROP");
                cropIntent.setDataAndType(picUri, "image/*");
                cropIntent.putExtra("crop", "true");
                cropIntent.putExtra("aspectX", 100);
                cropIntent.putExtra("aspectY", 100);
                cropIntent.putExtra("outputX", 256);
                cropIntent.putExtra("outputY", 256);
                cropIntent.putExtra("return-data", true);
                startActivityForResult(cropIntent, PIC_CROP);
            }
            catch(ActivityNotFoundException anfe){
                String errorMessage = "Извините, но ваше устройство не поддерживает кадрирование";
                Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
