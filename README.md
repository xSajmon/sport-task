# Sport task
Simple app that returns by default 10 most probable match results for UEFA Champions League. Input is attached in json file. 


## Running app

Clone the project

```
git clone https://github.com/xSajmon/sport-task.git
```
Go to the project directory

```
cd sport-task
```

Run
 ```
 mvn spring-boot:run   
 ```
 
 
## Configuration

Return specific number of events

```bash
  mvn spring-boot:run "-Dspring-boot.run.arguments=--events.number=5"    
```

Use different data file source
```bash
  mvn spring-boot:run "-Dspring-boot.run.arguments=--classpath:data.file=test.json"   
```


## API

#### Get all items

```
  GET /events
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `limit` | `number` | Optional |

#### Get event names

```
  GET /events/name
```



