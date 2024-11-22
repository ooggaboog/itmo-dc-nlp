require: dateTime/dateTime.sc
  module = sys.zb-common

theme: /

    state: Start
        intent: /start
        q!: *start
        q!: * (привет/здравствуй*/~добрый (~утро/~вечер/~день/~ночь)) *
        random:
            a: Привет, друг.
            a: Здравствуйте!
            a: Доброе время суток.
        go!: /ChoseTopic
        
    state: NoMatch
        event!: noMatch
        random:
            a: Я тебя не понял.
            a: Не понимаю, о чем ты.
            a: Я не могу тебе ответить.
        random:
            a: Попробуй сказать по-другому.
            a: Переформулируй свой запрос.
        go!: /ChoseTopic
        
    state: ChoseTopic
        a: Выбери, что ты хочешь узнать?
        buttons:
            "Текущую дату" -> /Date
            "Текущий день недели" -> /Week Day
    
    state: Date
        intent!: /Date
        q!: * (~дата/~число) *
        script:
            $temp.date = currentDate();
        a: {{$temp.date.locale("ru").format("L")}}
            
    state: Week Day
        intent!: /Weekday
        q!: * (~день недели) *
        script:
            $temp.date = currentDate();
        a: {{$temp.date.format("dddd")}}

