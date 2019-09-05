# Dagger for Dependency Injection

Dagger는 DI Framework 입니다. DI란 외부에서 의존 객체를 대신 생성하여 넘겨주는 것을 의미합니다. 
예를들어 Pet Class가 Dog Class에게 의존할 때 dog를 pet이 직접 생성하지 않아도 외부에서 대신 생성하여 넘겨주면 의존성을 주입했다고 말할 수 있습니다.
DI를 위해서는 객체를 생성하고 넘겨주는 일을 외부에 대신해줄 수 있는 무언가가 필요합니다. 이것이 DI Framework가 하는 일입니다.
외부에서 넘겨주는것을 Dagger에서는 Component와 Module이라고 부릅니다. 

DI가 필요한이유 :

의존성 파라미터를 생성자에 작성하지 않아도 되므로 보일러 플레이트 코드를 많이 줄일 수 있습니다. 개보일러 플레이트 코드를 줄이는 것만으로도 유연한 프로그래밍이 가능합니다.

Interface에 구현체를 쉽게 교체하면서 상황에 따라 적절한 행동을 정의할 수 있습니다. 이것은 특히 Mock 객체와 실제 객체를 바꿔가며 테스트 할 때 유용합니다.
그렇다면 안드로이드에서 DI 구현을 돕는 라이브러리 Dagger에 대해 알아보겠습니다.

자세한 설명은 http://jakewharton.github.io/butterknife 에서 확인 가능합니다.

- build.gradle (Module:app)에서 아래와 같이 추가합니다.

  ```bash
  
  android {
    ...
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
  }
  
  dependencies {
    implementation 'com.jakewharton:butterknife:10.1.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
  }

  ```
  
  종속성 추가후 appcompat에서 빨간밑줄로 오류가 날수도있다. 버터나이프도 androidx와의 호환을 위하여 업데이트 된 모양이다.
  상단의 Refactor -> Migrate to Androidx -> Do Refactor를 해줍니다. 그러고 다시 Sync버튼을 누르면 오류는 사라집니다.
  
  
- ButterKnife를 적용시키자

  ```bash
  
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      ButterKnife.bind(this);
  }
 
  ```
  ButterKnife를 적용시킬 Activity에서 위와같이 한번에 바인딩 해줍니다.


- 전체적인 코드설명 

  ```bash
  
   public class MainActivity extends AppCompatActivity {

     private static final String TAG = "MainActivity";
     private int count = 0;

     // 모든 뷰와 리소스들 사용을위해 아래와 같은 방법으로 작성합니다. (= findViewById(R.id)와 같음)
     
     @BindView(R.id.textView)
     TextView textView;
     
     @BindViews({ R.id.firstNameTextView, R.id.lastNameTextView })
     List<TextView> nameTextViews;

     @BindViews({ R.id.button1, R.id.button2, R.id.button3})
     List<Button> nameButtons;

     @BindString(R.string.app_name)
     String str;

     @BindView(R.id.imageView)
     ImageView imageView;

     @BindDrawable(R.drawable.ic_launcher_background)
     Drawable drawable;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        
        //아래는 Butterknife 라이브러리를 사용하지 않았을때의 코드입니다.
        //textView = findViewById(R.id.textView);
        //String str = getResources().getString(R.string.app_name);
        
        //바인딩을 시켜준후 아래와 같이 바로 사용가능합니다.
        textView.setText("blah blah blah...");
        textView.setText(str);
        imageView.setImageDrawable(drawable);
        
        //List형식으로도 바인딩을 시켜주고 아래와 같이 사용할 수도 있습니다.
        for( TextView textView : nameTextViews ) {
            Log.e(TAG, " on Create : " + textView.getText());
        }

        for( Button button : nameButtons ) {
            Log.e(TAG, "button : "+button.getText());
        }
    }

    //메소드를 지정해줄때도 다음과 같이 사용할수 있습니다. 한개 이상의 리소스를 지정해줄 수 있습니다.
    
    @OnClick(R.id.button)
    void buttonClick() {
        Toast.makeText(this,"button clicked", Toast.LENGTH_SHORT).show();
    }
    
    @OnLongClick(R.id.button)
     void onLongClick() {
        Toast.makeText(this ,"On Long Click",Toast.LENGTH_LONG).show();
    }
    
    @OnTouch({R.id.textView, R.id.firstNameTextView, R.id.lastNameTextView})
    void onTouch(View view){
        switch(view.getId()) {
            case R.id.textView:
                Toast.makeText(this, "on touch : textView Clicked" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.firstNameTextView:
                Toast.makeText(this, "on touch : firstNameTextView Clicked" , Toast.LENGTH_SHORT).show();
                break;
            case R.id.lastNameTextView:
                Toast.makeText(this, "on touch : lastNameTextView Clicked" , Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick({ R.id.button1, R.id.button2, R.id.button3})
     void OnCount() {
        Log.e(TAG, String.valueOf(count));
        count++;
    }
  }
  
  ```
 
  
  
 ### ButterKnife를 쓰고난후 느낀점
 
  안드로이드를 사용하면서 어노테이션을 사용할 일이 많이 없어서 어렵게 보였지만 사용하다보니 금방 적응하게되었습니다.
  그리고 짧게 코딩을 하였지만 확실히 간결하게 보입니다. 복잡한 프로젝트를 진행할때 이런식으로 코드를 사용할 수 있다면
  조금더 보기좋은 코드가 될수 있을것 같습니다.
