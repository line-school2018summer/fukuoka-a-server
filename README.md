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

# データベース内容

#### 通信チェック
#### GET HTTP/hello でhelloworld表示
## ＜ユーザＤＢ＞
|名前|データ型|データ内容|操作|
|:----|:----|:-----|:-----|
|UserId|Long|ユーザーの識別ＩＤ||
|UserName|String|ユーザーの登録名||
|UserEmail|String|ユーザーのメールアドレス|
|UserIconId|Long|ユーザーのアイコン画像ＩＤ|
|UserIconURL|String|ユーザーのアイコン画像URL|
フレンドかどうか(isFriend)はローカルのみ持つ
画像のパスを作る ストレージに持つ
### GET HTTP /Alluserget
curl -X GET http://localhost:80/Alluserget
[]
### POST HTTP /userpost/{UserId}/{UserName}/{UserEmail}/{UserIconURL}/userdata
Userデータの登録
UserIconIdは自動インクリメント
trueを返す
### DELETE HTTP /userdelete
ユーザーデータの全消去
curl -X DELETE http://localhost:80/userdelete
ALL USER DELETE

##### UserIDで名前のString取るGET HTTP '/userget/{Userid}/username'　

##### GET HTTP /userget/{Userid}/usericonurl　UserIDでアイコンURLのString取る

##### UserIDで全データ検索 GET HTTP `/userget/{Userid}/userdata`　









## ＜グループ管理ＤＢ＞
|Name|Type|内容|操作|
|:----|:----|:-----|:-----|
|GroupInfoId|Long|識別用ID|GET|
|UserId|Long|ユーザーID|GET|
|GroupId|Long|グループID|GET|

### DELETE HTTP /groupinfodelete

### GET HTTP /Allgroupinfo

### POST /groupinfopost/{UserId}/{GroupId}

##### GET/groupinfoid/{groupinfoid}/groupdata




## ＜グループＤＢ＞
|Name|Type|内容|操作|
|:----|:----|:-----|:-----|
|GroupId|Long|グループID|GET|
|GroupName|String|グループ名前|
|isGroup|Bool|グループかどうか|
|GroupIconURL|String|アイコンのＵＲＬ|

### DELETE HTTP /groupdelete

### GET HTTP /Allgroup

### POST /grouppost/{GroupId}/{GroupName}/{isGroup}/{GroupIconURL}

##### GET/group/{GroupId}/groupdata
アイコンのＵＲＬは１：１の時には
GroupId→UserId→UserIconとたどる
## ＜メッセージＤＢ＞
|Name|Type|内容|操作|
|:----|:----|:-----|:-----|
|MessageId|Long|メッセージのＩＤ||
|SenderId|Long|送信者のID||
|GroupId|Long|受信するルーム・個人のID||
|Message|Object(String)|メッセージ本体(画像なども含む)|
|MessageType|String|メッセージの種類|
|SendTime|Timestamp|送った日時||

- 使用方法 messageidが主キー
- roomidを入れてmessageidListを呼ぶ

### 全データ消去 /messagedelete


### データ送信 /messagepost/{SenderId}/{GroupId}/{Message}/messagedata


### 全データ検索 \Allmessage


### GET HTTP /messageid MessageIdの最大値を返す
curl -X GET http://localhost:80/messageid
1

##### GET HTTP /messageid/{messageId}/messagedata メッセージＩＤで全データ表示

##### GET HTTP /roomid/{roomid}/messageid　RoomIdでMessageId(List<Long>)検索

##### GET HTTP /messageid/{messageid}/message 

##### GET HTTP /messageid/{messageid}/senderid