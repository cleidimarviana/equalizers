# equalizers
Sound equalizer bars for Android

## Code XML

Attribute
```
custom:animDuration="5000" 					<-- Animation duration time  
custom:numberBars="25"						<-- Number of bars to display.
custom:foregroundColor="@color/darkdark"    <-- Color of the bars.
```

The View: 
```java
 <me.ack.Equalizers
        android:id="@+id/equalizers"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        custom:animDuration="5000"
        custom:numberBars="25"
        custom:foregroundColor="@color/darkdark"/>
```

## Screenshots

![alt tag](https://github.com/cleidimarviana/equalizers/blob/master/screenshots/gif_equalizers.gif "Equalizers")

Feedback
----
Questions, comments, and feedback are welcome at cleidimarviana@gmail.com

License
----

The MIT License (MIT)

Copyright (c) 2015 Cleidimar Viana 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
