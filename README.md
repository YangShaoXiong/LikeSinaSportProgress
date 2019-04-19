## Preview
![效果图](https://github.com/YangShaoXiong/LikeSinaSportProgress/blob/master/screenshot/image.gif)
## Usage
```java
<com.dreamer.ratioprogresslibrary.RatioProgressBar
	android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:left_progress_bg="@color/bg_left_progress"
    app:left_progress_value="1580"
    app:left_right_progress_spacing="1"
    app:progress_anim_duration="4000"
    app:progress_height="15dp"
    app:right_progress_bg="@color/bg_right_progress"
    app:right_progress_value="800" />
```
## Attributes
| name                        |  format   | description               | default_value |
| :--------------------------:| :------:  | :-----------:             | :-----------: |
| left_progress_bg            | color     | 左进度条的背景颜色          | #DD2F1C       |
| left_progress_value         | integer   | 左进度条的大小             | 0 |
| right_progress_bg           | color     | 右进度条的背景颜色         | #1D69E1|
| right_progress_value        | integer   | 右进度条的大小             | 0 |
| left_right_progress_spacing | integer   | 左右进度条之间的间隔距离(间隔 = n * progress_height)    | n = 1 |
| progress_anim_duration      | integer   | 进度条的动画时长（单位毫秒） | 3000 |
| progress_height             | dimension | 进度条的高度                | 15 |
## License
```
   Copyright 2016 Dreamer

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
