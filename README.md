# Dagger for Dependency Injection

Dagger는 DI Framework 입니다. DI란 외부에서 의존 객체를 대신 생성하여 넘겨주는 것을 의미합니다. 
예를들어 Pet Class가 Dog Class에게 의존할 때 dog를 pet이 직접 생성하지 않아도 외부에서 대신 생성하여 넘겨주면 의존성을 주입했다고 말할 수 있습니다.
DI를 위해서는 객체를 생성하고 넘겨주는 일을 외부에 대신해줄 수 있는 무언가가 필요합니다. 이것이 DI Framework가 하는 일입니다.
외부에서 넘겨주는것을 Dagger에서는 Component와 Module이라고 부릅니다. 

DI가 필요한이유  :
의존성 파라미터를 생성자에 작성하지 않아도 되므로 보일러 플레이트 코드를 많이 줄일 수 있습니다. 개발을 진행할때 보일러 플레이트 코드를 줄이는 것만으로도 유연한 프로그래밍이 가능합니다.

자세한 설명은 https://github.com/google/dagger 에서 확인 가능합니다.

1. build.gradle (Module:app)에서 아래와 같이 추가합니다.

  ```bash
  dependencies {
      ...
    api 'com.google.dagger:dagger:2.24'
    annotationProcessor 'com.google.dagger:dagger-compiler:2.24'
  }
  ```
  위와 같이 종속성을 추가시켜준후에 오른쪽 상단의 Sync now를 클릭해줍니다.
  
  
2. Module.class

  ```bash
   @dagger.Module
   public class Module {

    @Provides
    Pet provideSomething (Dog d) {
        return new Pet();
    }
    
  }
  ```
  의존성 문제를 해결하기 위해 인스턴스를 제공해야 합니다. 그 설정을 하는게 위의과정입니다.
  클래스 위에 @Module을 붙이고, 인스턴스를 제공하고자 하는것 위에 @Provides을 붙입니다. 
  @Provides의 반환 값은 주입하고자 하는 것의 형태가 됩니다. 이번에는 Pet 클래스를 주입하고 싶으므로 Pet을 반환값을 합니다.

3. Component.interface

  ```bash
   @dagger.Component(modules = Module.class)
   public interface Component {
          void inject(MainActivity mainActivity);
  }
  ```
 의존 해결을 하고 싶은 곳과 어떤 모듈을 사용할 것인지 정의합니다.
 @Component 의 인수로는 사용할 Module 클래스를 설정합니다.
 inject 메소드를 정의하여 인수에 의존 해결을 하는 클래스를 설정합니다. 위 코드는 MainActivity에서 의존을 해결하고싶은 예입니다.
 프로젝트를 재빌드하면 DaggerSampleComponent 클래스가 자동으로 생성되고 사용하시면 됩니다.
  
  
 ### Dagger를 사용하고 느낀점
  dagger2를 처음접했을때 저에게는 학습비용이 꽤나 높은 개념이였습니다.
  유용한 많은 기능들이 있고, 위의 예제는 간단한 예시이지만 규모가 커지는 프로젝트에서 dagger는 엄청난 효과를 발휘할 것으로 생각합니다.
