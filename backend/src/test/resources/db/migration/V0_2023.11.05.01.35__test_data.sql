insert into public.forecast (id, date, created_at, modified_at)
values  ('8d78ec4a-63ff-42b9-aa31-7307f0ed5496', '2023-11-05', '2023-11-04 21:30:44.523297', '2023-11-04 23:47:44.514041'),
        ('aa72c09e-470f-481c-870f-b55354507878', '2023-11-06', '2023-11-04 21:30:44.729423', '2023-11-04 23:47:44.883735'),
        ('99d98edc-7f8b-48d3-b5f6-32ed975732c6', '2023-11-07', '2023-11-04 21:30:44.749627', '2023-11-04 23:47:44.914215'),
        ('3abd8003-431d-4f33-81fb-cba415159173', '2023-11-08', '2023-11-04 21:30:44.786367', '2023-11-04 23:47:44.944421');


insert into public.forecast_statement (id, forecast_id, type, phenomenon, statement, min_temperature, max_temperature, created_at, modified_at)
values  ('681069a9-3439-4140-83ac-0202f593a601', '8d78ec4a-63ff-42b9-aa31-7307f0ed5496', 'DAY', 'Cloudy with clear spells', 'Partly cloudy and mainly dry. In the evening becoming cloudy with rain in places. Southerly wind 2-8, on western islands southeast wind 5-11, in the evening in gusts up to 15 m/s. Air temperature 5..9°C.', 5, 9, '2023-11-04 21:30:44.548879', '2023-11-04 23:47:44.614169'),
        ('4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', '8d78ec4a-63ff-42b9-aa31-7307f0ed5496', 'NIGHT', 'Light shower', 'It is becoming brighter. Locally rain, towards morning appears fog patches. In mainland south, southeast wind 5-10, in gusts up to 16, on islands and wetern coast south, southwest 7-13, in gusts up to 20 m/s, after midnight decreasing and backing everywhere to southeast 2-8, on islands in gusts 12 m/s. Air temperature 1..5, in Western Estonia and on coast to 8°C.', 1, 8, '2023-11-04 21:30:44.657616', '2023-11-04 23:47:44.780566'),
        ('bddddfb1-f975-4b7b-9e84-317a9e552411', 'aa72c09e-470f-481c-870f-b55354507878', 'DAY', 'Moderate rain', 'Mostly cloudy. Rain in many places. Easterly, in the afternoon variable wind 2-7 m/s. Air temperature 5..9°C.', 5, 9, '2023-11-04 21:30:44.736459', '2023-11-04 23:47:44.894798'),
        ('2ad47f70-35bb-4f1e-8703-6fa84df7946f', 'aa72c09e-470f-481c-870f-b55354507878', 'NIGHT', 'Moderate rain', 'Cloudy with some clear spells. Before midnight locally, after midnight in many places rain. Southeast, east wind 3-9, before midnight on western coast in gusts 13 m/s. Air temperature 3..7°C.', 3, 7, '2023-11-04 21:30:44.743343', '2023-11-04 23:47:44.906512'),
        ('02fce910-f04e-4643-aa79-eccfe3438617', '99d98edc-7f8b-48d3-b5f6-32ed975732c6', 'DAY', 'Moderate rain', 'Cloudy with some clear spells. Rain at times. Westerly wind 4-9, on western islands and in area of Gulf of Riga southwest wind in gusts to 12 m/s. Air temperature 6..10°C.', 6, 10, '2023-11-04 21:30:44.756399', '2023-11-04 23:47:44.925442'),
        ('5eee6505-4a90-4d9c-bec9-b8822dfb551f', '99d98edc-7f8b-48d3-b5f6-32ed975732c6', 'NIGHT', 'Moderate rain', 'Overcast with rain in many places. Variable wind 1-6, after midnight becoming around west 3-8, in the morning on Saaremaa coast and in area of Gulf of Riga southwest wind in gusts 11 m/s. Air temperature 3..8°C.', 3, 8, '2023-11-04 21:30:44.772135', '2023-11-04 23:47:44.936074'),
        ('2d320f94-876a-40ff-a2f8-2653d72f5ab4', '3abd8003-431d-4f33-81fb-cba415159173', 'DAY', 'Moderate shower', 'Cloudy with clear spells. Rain at times, in evening precipitation reducing. Westerly wind 3-9, in forenoon in gusts to 12 m/s. Air temperature 6..9°C.', 6, 9, '2023-11-04 21:30:44.795842', '2023-11-04 23:47:44.963018'),
        ('58e83aef-c4a9-4988-9604-85a15b699c0a', '3abd8003-431d-4f33-81fb-cba415159173', 'NIGHT', 'Moderate rain', 'Cloudy with some clear spells. Rain at times. Southerly wind 3-9, on western islands and in area of Gulf of Riga southwest wind in gusts 13 m/s. Air temperature 3..8°C.', 3, 8, '2023-11-04 21:30:44.808946', '2023-11-04 23:47:44.974497');

insert into public.place (id, name, created_at, modified_at)
values  ('2f68f323-b6b3-4741-b525-e1a6469f6739', 'Harku', '2023-11-04 21:30:44.558214', '2023-11-04 21:30:44.558214'),
        ('16e4a582-b9ed-4705-9326-aeb38d3d9966', 'Jõhvi', '2023-11-04 21:30:44.574295', '2023-11-04 21:30:44.574295'),
        ('47f61e7b-d78d-4c2a-8e6e-fc2d69b8d6fb', 'Tartu', '2023-11-04 21:30:44.592764', '2023-11-04 21:30:44.592764'),
        ('dffc6362-b89c-42e8-93b8-1539b42965c7', 'Pärnu', '2023-11-04 21:30:44.612351', '2023-11-04 21:30:44.612351'),
        ('589e61e8-8e33-4bbf-a6e8-3a20571456f6', 'Kuressaare', '2023-11-04 21:30:44.626965', '2023-11-04 21:30:44.626965'),
        ('d8aaac3c-d133-4414-a4ee-2d3874a8e2ef', 'Türi', '2023-11-04 21:30:44.642351', '2023-11-04 21:30:44.642351');

insert into public.place_statement (id, forecast_statement_id, place_id, phenomenon, min_temperature, max_temperature, created_at, modified_at)
values  ('a1c70595-dfe7-4aea-8ab9-f46e7cfa024b', '4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', '47f61e7b-d78d-4c2a-8e6e-fc2d69b8d6fb', 'Few clouds', 2, null, '2023-11-04 21:30:44.695245', '2023-11-04 23:47:44.831091'),
        ('386e4d3e-e8d5-4ee3-aded-d1aca05a932e', '4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', 'dffc6362-b89c-42e8-93b8-1539b42965c7', 'Light shower', 4, null, '2023-11-04 21:30:44.704288', '2023-11-04 23:47:44.847334'),
        ('21ef90ea-ee34-4442-8f96-3d9a046e2723', '4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', '589e61e8-8e33-4bbf-a6e8-3a20571456f6', 'Light shower', 8, null, '2023-11-04 21:30:44.714214', '2023-11-04 23:47:44.861127'),
        ('0b5b4a41-7c92-43bd-9808-6a60e61e72a1', '4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', 'd8aaac3c-d133-4414-a4ee-2d3874a8e2ef', 'Cloudy with clear spells', 4, null, '2023-11-04 21:30:44.723126', '2023-11-04 23:47:44.874117'),
        ('d0e85bea-f6cd-4c1f-b338-937e30bb5114', '681069a9-3439-4140-83ac-0202f593a601', '2f68f323-b6b3-4741-b525-e1a6469f6739', 'Cloudy with clear spells', null, 8, '2023-11-04 21:30:44.566817', '2023-11-04 23:47:44.647964'),
        ('2b7b1f3a-16b7-4efa-8484-0e52127792ab', '681069a9-3439-4140-83ac-0202f593a601', '16e4a582-b9ed-4705-9326-aeb38d3d9966', 'Light shower', null, 8, '2023-11-04 21:30:44.581135', '2023-11-04 23:47:44.677798'),
        ('d8d1cbf7-3319-4207-ba73-a6b8b7de031f', '681069a9-3439-4140-83ac-0202f593a601', '47f61e7b-d78d-4c2a-8e6e-fc2d69b8d6fb', 'Variable clouds', null, 8, '2023-11-04 21:30:44.601161', '2023-11-04 23:47:44.703838'),
        ('c391c8a4-2018-4e0e-b8c9-80f97d6b38a2', '681069a9-3439-4140-83ac-0202f593a601', 'dffc6362-b89c-42e8-93b8-1539b42965c7', 'Variable clouds', null, 7, '2023-11-04 21:30:44.619874', '2023-11-04 23:47:44.725688'),
        ('de48e046-6788-46d7-8b0b-9008d50872ed', '681069a9-3439-4140-83ac-0202f593a601', '589e61e8-8e33-4bbf-a6e8-3a20571456f6', 'Variable clouds', null, 8, '2023-11-04 21:30:44.635345', '2023-11-04 23:47:44.747588'),
        ('a0c1f4d3-4b03-4d21-b0ac-d363774c46e4', '681069a9-3439-4140-83ac-0202f593a601', 'd8aaac3c-d133-4414-a4ee-2d3874a8e2ef', 'Variable clouds', null, 8, '2023-11-04 21:30:44.649904', '2023-11-04 23:47:44.766082'),
        ('04ead159-8837-494d-888b-697ea6ab342c', '4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', '2f68f323-b6b3-4741-b525-e1a6469f6739', 'Light rain', 6, null, '2023-11-04 21:30:44.674350', '2023-11-04 23:47:44.797832'),
        ('33cd511f-d476-49e2-86ba-4676fdedebe7', '4d7d8db6-cf24-4369-890b-c8afdcb2fd6c', '16e4a582-b9ed-4705-9326-aeb38d3d9966', 'Fog', 4, null, '2023-11-04 21:30:44.685929', '2023-11-04 23:47:44.814997');