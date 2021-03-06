
# AndroidLab
[Lab1 HelloWorld! & ActivityLifecycle](#lab1) 

[Lab2 Layout 布局实验](#lab2) 

[Lab3 UI Component](#lab3)

[Lab4 扩展的Activity](#lab4)

[Lab5 intent](#lab5)
## Lab1 
HelloWorld! & ActivityLifecycle 
### HelloWorld!
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/hello.png" width = "30%" height = "30%" div align=center />

### ActivityLifecycle
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/lifecycle1.PNG"  div align=center />
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/lifecycle2.PNG"  div align=center />

---

## Lab2 
Layout 布局实验
### linear layout [xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/layout/linearlayout.xml)
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/linear.png" width = "30%" height = "30%" div align=center />

### constraint layout [xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/layout/constraintlayout.xml)
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/constraint.png" width = "30%" height = "30%" div align=center />

### table layout [xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/layout/tablelayout.xml)
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/table.png" width = "30%" height = "30%" div align=center />

## Lab3 
UI component
### ListView [JavaFile](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/java/com/example/androidlab/ListViewActivity.java) [   ListItem_xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/layout/simple_item.xml)
* Primary Code
```java
//创建List集合，集合元素是Map
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for (int i=0; i<names.length;i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("header",imageIds[i]);
            listItem.put("name",names[i]);
            listItems.add(listItem);
        }
        //创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.simple_item,
                new String[] {"header","name"},
                new int[] {R.id.header,R.id.name});
        ListView list = findViewById(R.id.myListView);
        // 为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        // 为ListView的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {   // Toast是Android中的一种简易消息提示框
                Toast.makeText(getApplicationContext(), names[position], Toast.LENGTH_LONG).show();
            }
        });
```
* Screenshot
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/listview.png" width = "30%" height = "30%" div align=center />

### AlterDialog [JavaFile](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/java/com/example/androidlab/MainActivity.java)[  AlertDialog_xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/layout/activity_alert_dialog.xml)
* Primary Code
```java
// lab3_2 Alert Dialog
    // references (https://developer.android.com/guide/topics/ui/dialogs)
    public void BuildAlertDialog(View view){
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_alert_dialog, null))
                // Add action buttons
                .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
```
* Screenshot
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/alertdialog.png" width = "30%" height = "30%" div align=center />

### OptionMenu [JavaFile](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/java/com/example/androidlab/MenuTest.java)[     OptionMenu_xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/menu/menu_with_xml.xml)
* Primary Code
```java
@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_with_xml, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        EditText edt = findViewById(R.id.text_menutest);
        switch (item.getItemId()) {
            case R.id.font_big:
               edt.setTextSize(20);
                return true;
            case R.id.font_median:
                edt.setTextSize(16);
                return true;
            case R.id.font_small:
                edt.setTextSize(10);
                return true;
            case R.id.color_red:
                edt.setTextColor(Color.parseColor("#FF0033"));
                return true;
            case R.id.color_black:
                edt.setTextColor(Color.parseColor("#000000"));
                return true;
            case R.id.item_normal:
                Toast.makeText(getApplicationContext(), "选中普通菜单项", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
```
* Screenshot

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/optionmenu1.png" width = "30%" height = "30%" div align=center />   <img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/optionmenu2.png" width = "30%" height = "30%" div align=center />

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/optionmenu3.png" width = "30%" height = "30%" div align=center />  <img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/optionmenu4.png" width = "30%" height = "30%" div align=center />

### Contextual Menu [JavaFile](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/java/com/example/androidlab/ContextualMenuActivity.java)  [    ContextualMenu_xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/menu/contextual_menu.xml)
* Primary Code
```java
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        list.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            private int nr = 0;

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
                mAdapter.clearSelection();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub

                nr = 0;
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.contextual_menu, menu);
                return true;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // TODO Auto-generated method stub
                switch (item.getItemId()) {

                    case R.id.delete_item:
                        nr = 0;
                        mAdapter.clearSelection();
                        mode.finish();
                }
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // TODO Auto-generated method stub
                if (checked) {
                    nr++;
                    mAdapter.setNewSelection(position, checked);
                } else {
                    nr--;
                    mAdapter.removeSelection(position);
                }
                mode.setTitle(nr + " selected");

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                // TODO Auto-generated method stub

                list.setItemChecked(position, !mAdapter.isPositionChecked(position));
                return false;
            }
        });
```
* Screenshot
<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/contextualmenu.png" width = "30%" height = "30%" div align=center />

## Lab4
扩展的Activity
[PreferenceActivity.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/java/com/example/androidlab/PreferenceActivity.java)
[res/xml/preference.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/xml/preference.xml)
* Screenshot

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/preference1.png" width = "30%" height = "30%" div align=center />    <img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/preference2.png" width = "30%" height = "30%" div align=center />
### Edit Preference
* xml code

res/xml/preference.xml
```xml
<EditTextPreference
            android:defaultValue="false"
            android:dialogTitle="Enter your favourite animal"
            android:key="edit_text_preference_1"
            android:selectAllOnFocus="true"
            android:singleLine="true"
            android:summary="An example that uses edit text dialog "
            android:title="Edit text preference" />
```

* Screenshot

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/editPref.png" width = "30%" height = "30%" div align=center />

### List Preference
* xml code

res/xml/preference.xml
```xml
<ListPreference
            android:defaultValue="1"
            android:entries="@array/settings_list_preference_titles"
            android:entryValues="@array/settings_list_preference_titles"
            android:key="list_preference_1"
            android:title="List preference" />
```
[res/values/arrays.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/values/arrays.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string-array name="settings_list_preference_titles">
        <item>Alpha Option 01</item>
        <item>Beta Option 02</item>
        <item>Charlie Option 03</item>
    </string-array>
</resources>
```
* Screenshot

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/listPref.png" width = "30%" height = "30%" div align=center />


### Screen preference

* java code

PreferenceActivity.java
```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings_container, new MySettingsFragment())
                .commit();
    }

    @Override
    public boolean onPreferenceStartFragment(PreferenceFragmentCompat caller, Preference pref) {
        // Instantiate the new Fragment
        final Bundle args = pref.getExtras();
        final Fragment fragment = new newFragment();
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        // Replace the existing Fragment with the new Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.settings_container, fragment)
                .addToBackStack(null)
                .commit();
        return true;
    }
    public static class MySettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.preference, rootKey);
        }
    }
    public static class newFragment extends PreferenceFragmentCompat{
        @Override
        public void onCreatePreferences(Bundle savedInstanceState,String rootKey){
            setPreferencesFromResource(R.xml.newprefernce,rootKey);
        }
    }
```
* xml code

res/xml/preference.xml
```xml
<Preference
            android:fragment="com.example.androidlab.PreferenceActivity$newFragment"
            android:key="screenPreferencs"
            android:title="Screen preference"
            android:summary="show another screen of preferences"/>
```
[res/xml/newPreference.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/res/xml/newprefernce.xml)
```xml
<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android">

    <CheckBoxPreference
        android:defaultValue="false"
        android:key="toggle_preference"
        android:summary="Preference that is on the next screen but same hierarchy"
        android:title="Toggle Preference" />
</PreferenceScreen>
```
* Screenshot

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/preLaunch.png" width = "30%" height = "30%" div align=center />

### Intent Preference
* xml code

res/xml/preference.xml
```xml
<Preference
            android:key="webpage"
            android:title="Intent preference"
            android:summary="Launchs an Activity from an Intent">
        <intent
            android:action="android.intent.action.VIEW"
            android:data="http://www.baidu.com" />
</Preference>
```

### Preference attributes
* xml code

res/xml/preference.xml
```xml
<CheckBoxPreference
            android:defaultValue="false"
            android:key="parent_check_box_preference"
            android:title="Parent check box preference"
            android:summary="This is visually a parent"/>
        <CheckBoxPreference
            android:defaultValue="false"
            android:key="child_check_box_preference"
            android:title="Child check box preference"
            android:summary="This is visually a child"
            android:dependency="parent_check_box_preference"/>
```
* Screenshot 

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/preDependecy1.png" width = "30%" height = "30%" div align=center />   <img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/preDependecy2.png" width = "30%" height = "30%" div align=center />

## Lab5
intent and webview
* [AndroidLab/.../webview.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/app/src/main/java/com/example/androidlab/webview.java)
```java
public void ToWebview(View view){
        EditText edt = findViewById(R.id.webviewEdt);
        String text =  edt.getText().toString();
        if (!text.isEmpty()) {
            // Create the text message with a string
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra("msg", text);
            sendIntent.setType("text/plain");

            // Verify that the intent will resolve to an activity
            if (sendIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(sendIntent);
            }
        }
    }
```
* [MyBrowser/.../MainActivity.java](https://github.com/FreedomHappy/AndroidDevelop/blob/master/MyBrowser/app/src/main/java/com/example/mybrowser/MainActivity.java)
```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String  uri = "http://www.baidu.com";
        if (intent!=null){
            String msg = intent.getStringExtra("msg");
            if (!(msg==null||msg.isEmpty())){
                uri = msg;
            }
        }
        //如果访问的页面中有 Javascript,则 WebView 必须设置支持 Javascript。
        myWebView.getSettings().setJavaScriptEnabled(true);
        // 如果页面中链接,如果希望点击链接继续在当前browser中响应,
        // 而不是新开Android的系统browser中响应该链接,必须覆盖 WebView的WebViewClient对象。
        myWebView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        myWebView.loadUrl(uri);
    }
```

* [MyBrowser/.../AndroidManifest.xml](https://github.com/FreedomHappy/AndroidDevelop/blob/master/MyBrowser/app/src/main/AndroidManifest.xml)

```xml
<activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.SEND"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <data android:mimeType="text/plain"/>
            </intent-filter>
</activity>
```


* Screenshot

<img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/intent1.png" width = "30%" height = "30%" div align=center />     <img src="https://github.com/FreedomHappy/AndroidDevelop/blob/master/AndroidLab/images/mybrowser.png" width = "30%" height = "30%" div align=center />

