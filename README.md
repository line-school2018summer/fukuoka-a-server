# apiSample

## 環境

- Java 10.0.2
- Kotlin 1.2.41
- Spring Boot 2.0.4
- Gradle 4.8.1
- IntelliJ IDEA 2018.2


## 簡易デプロイ＋起動方法(組込Tomcatを使用)
- IntelliJ IDEAのコンソールからGradleビルド
```
>gradlew build
```

- build/libs/ 下の apiSample-0.0.1-SNAPSHOT.jar を /home/ec2-user/server 下に転送

- jarを起動
```
$ cd ~/fukuoka-a
$ sudo java -jar ./apiSample-0.0.1-SNAPSHOT.jar
```

- Started ApiSampleApplicationKt メッセージを確認して curl -X GET http://{AWSサーバドメイン}/user で動作確認

## 動作確認
```
$ curl -X GET http://{AWSサーバドメイン}/user
{"greeting": "Hello World!"}

$ curl -X GET http://{AWSサーバドメイン}/user/1/profile
{"id":1,"name":"Atsushi Kimura","email":"akimura@potkitchen.com","created_at":"2018-08-08T00:00:00.000+0000","updated_at":"2018-08-08T00:00:00.000+0000"}
```

## APIリファレンス

### GET `/user`
> 動作確認用

#### Request

#### Response

| Parameter | Type | Description |
| -------- | -------- | -------- |
| greeting | String | Hello World! |

### GET `/userget/{UserId}/userdata`
> 指定した数字のUserIdの情報をとってくる

#### Request

| Parameter | Type | Description |
| -------- | -------- | -------- |
| UserId | Long | |

#### Response

|名前|データ型|データ内容|
|:----|:----|:-----|
|UserId|Long|ユーザーの識別ＩＤ|
|UserName|String|ユーザーの登録名|
|UserEmail|String|ユーザーのメールアドレス|
|UserIconId|Long|ユーザーのアイコン画像ＩＤ|

### GET `/messageget/{SenderId}/messagedata`
> 指定したSenderIdの情報をとってくる

#### Request

| Parameter | Type | Description |
| -------- | -------- | -------- |
| SenderId | Long | |

#### Response

|名前|データ型|データ内容|
|:----|:----|:-----|
|SenderId|Long|送信者のID|
|RoomId|Long|受信するルーム・個人のID|
|RoomType|String|受信するルームのタイプ|Use
|Message|Object(String)|メッセージ本体(画像なども含む)|
|MessageType|String|メッセージの種類|
|MessageId|Long|メッセージのＩＤ|
|SendTime|Timestamp|送った日時|





