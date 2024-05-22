create schema if not exists maverick;
use
maverick;
insert into rooms (id, room_type, bed_type, number_of_rooms, number_of_guests, price)
values (1, 'Deluxe', 'single bed', 10, 1, 100);


insert into gallery(image)
values ('https://campmaverick.files.wordpress.com/2024/01/whatsapp-image-2023-12-21-at-11.18.31-am.jpeg'),
       ('https://campmaverick.files.wordpress.com/2024/01/whatsapp-image-2023-12-21-at-10.35.24-am.jpeg'),
       ('https://campmaverick.files.wordpress.com/2023/01/gura-true.jpg'),
       ('https://campmaverick.files.wordpress.com/2023/02/njangiri-iv.jpg'),
       ('https://campmaverick.files.wordpress.com/2023/02/njangiri-falls-i.jpg'),
       ('https://campmaverick.files.wordpress.com/2023/02/njangiri-vii.jpg'),
       ('https://campmaverick.files.wordpress.com/2023/02/maverick-camp-huts.jpeg'),
       ('https://campmaverick.files.wordpress.com/2023/02/aberdares-ranges.jpg'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/283361471_3260215737596626_7598239292506245919_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=d8d9c5&_nc_ohc=bJ51LDGlma0AX-rIZxK&_nc_ht=scontent-lhr8-1.xx&oh=00_AfDCMBahHv-ROHs-wJUCjQLXn0ScohuVgzbL6h3z-P9HYA&oe=65DFA7C7'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/339122927_2547478095402993_8738414322038446258_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=783fdb&_nc_ohc=ygk8Wt7sKAQAX8fPKka&_nc_ht=scontent-lhr6-1.xx&oh=00_AfBWTl1-TPaIWg-MOYR7EzLwK6CW-XGJFJM4vUVxqVflWA&oe=65DF3180'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/319701144_2880449932091742_1851243413307242906_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=3635dc&_nc_ohc=XZfOsnOU_cEAX9QSGkp&_nc_ht=scontent-lhr6-1.xx&oh=00_AfBlPnP5n4bdEd0Q6i-353BG4fntiF5NNfweC_qnzFE6og&oe=65DF6009'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/318489872_3342514215970891_962528294433308806_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=3635dc&_nc_ohc=TC8g4uc4qMsAX9OHbBV&_nc_ht=scontent-lhr6-1.xx&oh=00_AfAI0fndcRnKa1sTJyxKpGl3XYwdM2vuwtGi_Org8jH1_A&oe=65DDEADD'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/316420554_543548171115077_5692906972117894732_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=3635dc&_nc_ohc=Sih7Tlf30N4AX_kcf0u&_nc_oc=AQkiFf1qZbvVyWyUd9CpRCtjM7UPe_2lXfrsLNoXctXw6XKfTSZ4-3N-bsJ91Et3ido&_nc_ht=scontent-lhr8-1.xx&oh=00_AfAkpK2-W8wvjKIczcTJFcJ6Mwzb4MojBv_glZ9TUsxoTw&oe=65DDEFCF'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/312814939_517162490420312_267281180419695000_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=s3Og78qc_uYAX97kARa&_nc_ht=scontent-lhr6-2.xx&oh=00_AfCxKLyJU2lLG3cS1D_hq_WY90ZCzcAY9o7i-eX71V1uHg&oe=65DE147A'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/312625473_517162617086966_8983272934872334698_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=7Xbos2YkE1IAX8BHgIN&_nc_ht=scontent-lhr6-1.xx&oh=00_AfBxwly0Y5xQg0cteGWEFlkSOCm_Pq_-xTwPlcgSm9g3lg&oe=65DDDA03'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/312807849_517162447086983_7400340267210207189_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=zuB0sqAkf1YAX-sdTZN&_nc_ht=scontent-lhr8-1.xx&oh=00_AfCGbaGp67_pbn4IjWutN5fCBHLrRbIPhuIYHsDN9TZ4gQ&oe=65DF1B2B'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/312807305_517162380420323_4884727773481386571_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=m9CjwZzKn6gAX_io14_&_nc_ht=scontent-lhr8-1.xx&oh=00_AfAF9gbXouWMv_Igo3CdjkD1w2GRva1vJz1TSgw1HdUuLA&oe=65DE7B54'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/312629067_517162543753640_7530554363312219496_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=ckVwIxTCuPsAX-SD0-Z&_nc_ht=scontent-lhr8-1.xx&oh=00_AfBE3njHNh-8RP_Y5S8JDwJ5tTcmA8LnUK_-KYt96QVcWA&oe=65DF678A'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/312809471_517162437086984_6552817896989613623_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=Uh9y23FAB14AX-iIsFK&_nc_ht=scontent-lhr6-2.xx&oh=00_AfBMD-cLeFQrQKONpqK2R0cwIm6rK-2hGd_L3yFO-5P1dA&oe=65DF298F'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/312522320_517162403753654_8110861075891243348_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=Ek9BZHTeA0EAX-FYqra&_nc_ht=scontent-lhr6-2.xx&oh=00_AfBb1VRE9XuEoA_49sEY-DhL3xeE-s0mCUGSyxBX7Cl4sg&oe=65DE66F1'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/312716211_517162533753641_945873044487850759_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=KdjAM33DNIgAX-3TvYK&_nc_ht=scontent-lhr6-2.xx&oh=00_AfBSJbTEo2HRNWqmxZAYD9BvWK_ZogdFHT-Ay18yVK6BQA&oe=65DEE1C3'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/271607904_3147104395574428_3883064872177929220_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=HuCXo61LvNwAX9g60sC&_nc_ht=scontent-lhr6-1.xx&oh=00_AfCaLFz3KrykAxbihHQmPKhD4nF8XiFl8yBUcfaswTKkSg&oe=65DDF399'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/271605651_3147104442241090_6709501422883520476_n.jpg?_nc_cat=103&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=JRgs4DHEcfQAX89juoV&_nc_ht=scontent-lhr8-1.xx&oh=00_AfASzk9ImQySObLFxkotA9YZ91jGhq3ixwbgyP0ZAQ9kEg&oe=65DE0A45'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/271669028_3147104585574409_8968765287279792698_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=NziEqHE-c9sAX_fayeq&_nc_ht=scontent-lhr6-1.xx&oh=00_AfA4QDjYWIEB1VVZ4Za2dbdPTXTKURs2glwRqRQUYHnPIg&oe=65DF7DD9'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/271708030_3147104252241109_2750449249477963635_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=dgp6gV9Cs_4AX-ZA_Pg&_nc_ht=scontent-lhr8-1.xx&oh=00_AfBALGHnmN-meoY1aVV9iB_YqJ5pBhS_6YjOGqUfmHAahw&oe=65DF855B'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/271606354_3147104608907740_6704593544758710822_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=zldQ1Ku6x2sAX8saC8q&_nc_oc=AQlVc2q0imSueI7qrQsgYhETalBVRU-Enzwo-H_zakqH_6R6FFDLYRnNdApP_QI07Ws&_nc_ht=scontent-lhr6-2.xx&oh=00_AfCmlFZzUnm2Ac1x-S0uM7MDYRKfM1p7QyF7ZWqqJPfF7g&oe=65DF6E1B'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/258533572_3110561129228755_7759460380830219752_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=3635dc&_nc_ohc=ecDwT_sJiWgAX9FDLOx&_nc_ht=scontent-lhr6-1.xx&oh=00_AfCb1Tud3eqlGYbkY8P3LWjZ3JdwKcqSdaFA7nQTTk4SRw&oe=65DF7887'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/258843886_3110561235895411_1774253799963659333_n.jpg?_nc_cat=110&ccb=1-7&_nc_sid=3635dc&_nc_ohc=Ry5IFn1TgrIAX_KHsEs&_nc_oc=AQk4JSwnLePfF4lqnKJct6ArQqo-2RnT6HGiw_CBuI8bYGeSDl9QtJ2HVpsuYReCyt0&_nc_ht=scontent-lhr6-1.xx&oh=00_AfCKGbCWpUs2KFrT0Ddi-Dzpn8qaWoGfbFby2Chf8QLR8A&oe=65DE58D2'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/258529710_3110560832562118_7209116702987244576_n.jpg?_nc_cat=100&ccb=1-7&_nc_sid=3635dc&_nc_ohc=XxzD4FLTBvYAX8pQXBR&_nc_ht=scontent-lhr6-2.xx&oh=00_AfDikvgH7ghu1eyvn6N7b6NURxYz-6l6I6DEonKMtpe_lQ&oe=65DED896'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/244835279_3070333153251553_407025501745704117_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=r-0K8JT0MNwAX8qQd0N&_nc_ht=scontent-lhr8-1.xx&oh=00_AfAMKlnna86fYIM51fzFTyz3WXILt1Ad5a7dHByV1g7Hmw&oe=65DECD90'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/244687391_3070333356584866_3185083952296235624_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=7DY6L-M-sNsAX9L1UX6&_nc_ht=scontent-lhr6-1.xx&oh=00_AfDo7C577FcoGznlD5t25ij4u1ppRNRKbUgiHg-Txm5-Cg&oe=65DE4CC5'),
       ('https://scontent-lhr6-1.xx.fbcdn.net/v/t39.30808-6/244687391_3070333356584866_3185083952296235624_n.jpg?_nc_cat=102&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=7DY6L-M-sNsAX9L1UX6&_nc_ht=scontent-lhr6-1.xx&oh=00_AfDo7C577FcoGznlD5t25ij4u1ppRNRKbUgiHg-Txm5-Cg&oe=65DE4CC5'),
       ('https://scontent-lhr8-2.xx.fbcdn.net/v/t39.30808-6/244636832_3070333329918202_1436228313851731074_n.jpg?_nc_cat=101&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=uqGC5m3LN4wAX_-g5kP&_nc_ht=scontent-lhr8-2.xx&oh=00_AfClFpnWgwZsa18WSsjBsSrO810tFD_ilnNclQ3MgKC_Bg&oe=65DF2052'),
       ('https://scontent-lhr6-2.xx.fbcdn.net/v/t39.30808-6/244627894_3070333169918218_8747041842559606932_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=eIuBAIoZSxcAX9IfsxZ&_nc_ht=scontent-lhr6-2.xx&oh=00_AfAyIx9m3MJgJZLuj5slR-xPUUBg6T0H1yXgylp_Qc8UHQ&oe=65DE6113'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/244872994_3070333136584888_8810414348852952394_n.jpg?_nc_cat=108&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=gZKHRWCYCDgAX-1xzEa&_nc_ht=scontent-lhr8-1.xx&oh=00_AfCouvqmjFZg9tfb8Tp-YnoOPqCZ6A3viGfRnXm3PnKdVg&oe=65DDD505'),
       ('https://scontent-lhr8-1.xx.fbcdn.net/v/t39.30808-6/244835279_3070333153251553_407025501745704117_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=dd5e9f&_nc_ohc=r-0K8JT0MNwAX8qQd0N&_nc_ht=scontent-lhr8-1.xx&oh=00_AfAMKlnna86fYIM51fzFTyz3WXILt1Ad5a7dHByV1g7Hmw&oe=65DECD90'),
       ('https://www.facebook.com/maverickventures/photos/a.2039849742966571/3057676894517179/?__cft__[0]=AZXX4hEL6DvFYau8GsdhQxVRH9-8SGpJqsoXoQP-FsVETHtEJW8EmDvYGOyNVUbmOohWWISdI9zOXprgmfAIMVv9INgUJrDWOLfgaqvmlgvrVTQ4EtVCujo3Skf0Qc-SqpqF3i1JnwJPFYWJlaVDGPZzkANqVAl974SBauEeaTCxSiXd--YNF4s-Lzfg0dpZWEtWVcyzSsSiiz1FHimsxW7x&__tn__=EH-R')



