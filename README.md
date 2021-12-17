# java-cptcw3

# Part A

在A部分，你需要编写一个GUI程序来模拟我们通过Internet传输数据时的数据封装。 您应该按照说明生成正确的输出。 它进一步分为两部分：A.1 部分和A.2 部分。

我们通常通过互联网传输数据包。通常，一个数据包由几个部分组成：帧头、帧尾、数据长度、数据和CRC码。

| HEader | data length | Data Segment |   CRC   |  tail  |
| :----: | :---------: | :----------: | :-----: | :----: |
| 1 Byte |   1 Byte    |   N Bytes    | 2 Bytes | 1 Byte |

在表1中，我们用```AA```和```BB```这2个十六进制常量分别表示一个数据包的头和尾（Header和Tail列），1个字节表示数据段的长度，2个字节来表示数据段的CRC码。

我们应该将数据封装到数据段中，并将数据包转换为十六进制字符串，然后再通过互联网发送数据。 

CRC 码用于验证数据包在传输过程中是否发生了变化。<br>数据封装的主要过程如下： 

1. 将数据串中的每个字符转换为十六进制值。

2. 计算十六进制串的CRC码。

3. 将头部、数据长度、数据段、CRC码和尾部组合成一个数据包。例如，我们想通过 Internet 向远程用户发送字符串```123A```。

   - 首先，我们应该将字符串```123A```转换为十六进制值```31323341```。 ```31```、```32```、```33```和```41```是数据串```123A```中每个字符的十六进制值。

   - 其次，我们可以根据十六进制字符串```31323341```计算得到一个CRC码```7BD7```。
   - 第三，我们将包头、尾和数据长度与十六进制值和CRC码结合起来，得到一个```AA04313233417BD7BB```的十六进制数据包。 ```AA```和```BB```是数据包的头和尾，```04```代表数据的长度。 ```31323341```是字符串```123A```的十六进制值，```7BD7```是CRC码。

```java
/*return a 2 byte CRC code from a byte array*/
private static String getCRC(byte[] bytes) {
    int CRC = 0X0000FFFF;
    final int POLYNOMIAL=0X0000A001;
    int i, j;
    for(i=0; i<bytes.length; i++){
        CRC = CRC^((int) (bytes[i] & 0x000000ff));
        for (j=0; j<8; j++){
            if ((CRC & 0X00000001) != 0){
                CRC = CRC>>1;
                CRC = CRC^POLYNOMIAL;
            } else{
                CRC = CRC>>1;
            }
        }
    }
    String result = Integer.toHexString(CRC).toUpperCase();
    if (result.length()!=4){
        StringBuffer sb = new StringBuffer("0000");
        result = sb.replace(4=result.length(), 4, result).toString();
    }

    return result.substring(2,4) + result.substring(0,2);
}
```



## A1

请按照以下步骤完成任务：

1. 创建一个空的java项目。项目名称应为```Packet```+您的学号。
2. 在你的项目中创建一个类```CRC16```。
   1. 将方法```getCRC(byte[] bytes)```复制到您的 CRC16 类中；
   2. 定义一个重载静态方法```getCRC(String hexData)```，它可以调用方法```getCRC(byte[] bytes)```并根据字符串```hexData```返回一个CRC码。 例如，从```31323341```的十六进制字符串中得到```7BD7```的CRC码。

3) 创建一个类```DataPacket```。
   1) 定义一个方法```stringToHex(String str)```将普通字符串转换为十六进制字符串。例如，将字符串```123A```转换为```31323341```。
   2) 定义一个方法```convertToDataPacket(String data)```，可以将一个字符串封装成一个数据包，返回一个16进制的字符串，如表1所示。 例如，如果我们使用参数```123A```调用该方法，将返回```AA04313233417BD7BB```。
   3)  定义一个方法```getDataFromDataPacket(String hexData)```，它可以从数据包的十六进制字符串中检索字符串数据。这方法应该检查数据包的头部和尾部的有效性，数据的长度和 CRC 码。 例如，如果我们使用```AA04313233417BD7BB```的参数调用该方法将返回```123A```。

4) 创建一个类```Q1```，在其main方法中实现如下功能。

   ![image-20211217041847574](imgSources\image-20211217041847574.png)
   1) 创建一个框架窗口，如下所示；
   2)  在main方法中创建一个DataPacket对象；
   3)   当用户点击```Data to packet```按钮时，可以将原始数据字段中的字符串封装成数据包字符串并显示在数据包字段中；
   4)   当用户点击```Packet to data```按钮时，它可以从数据包字段中检索数据字符串并显示在原始数据字段中。

## A2 部分 MultiDataPacket（20 分）

有时，我们需要在一个数据包内传输多个数据，以提高数据传输效率。 例如，如果我们在一个数据包中发送两个字符串```ABC```和```123A```，则该数据包的十六进制值为```AA034142435085BBAA04313233417BD7BB```。 

此外，我们应该在从中检索数据之前检查数据包的有效性。 如果数据包无效，我们应该抛出异常。 

请按照以下步骤完成任务。

1. 创建异常类```DataPacketException```，继承自```Exception```。
   1. 创建一个构造函数```DataPacketException(int id, String message)```来创建一个带有 id 和消息字符串的异常对象。
   2. 创建 1 个方法来从异常中检索 id。
   3. 覆盖方法```toString()```以返回带有 id 和消息的字符串。
   
2. 在基类```DataPacket```的基础上创建一个子类```MultiDataPacket```。
   1. 创建重载方法```String[] stringToHex(String[] str)```，可以将普通字符串数组转换为十六进制字符串数组。
   2. 创建重载方法```String convertToHexDataPacket(String[] data)```，可以将普通字符串数组转换为单个十六进制字符串的MultiDataPacket。
   3. 创建一个新方法```String[] getMultiDataFromHexDataPacket(String hexData)```以从 MultiDataPacket 字符串```hexData```中检索并返回多个数据。 该方法应验证字符串```hexData```中每个数据包的头、尾、长度和 CRC 码，并在任何验证失败时抛出```DataPacketException```异常。

3. 创建一个类```Q2```，在其main方法中实现如下功能。

   1. 创建一个框架窗口如下

      ![image-20211217042757985](imgSources\image-20211217042757985.png)

      您应该在窗口顶部列出您的个人信息（姓名、学生 ID 和性别）和您的照片。

   2. 在main方法中创建一个MultiDataPacket对象；

   3. 用户可以在 3 个文本字段（原始数据 1~原始数据 3）中输入字符串。

   4. 当用户点击```数据转多包```按钮时，可以将3个文本字段中的字符串封装成一个16进制字符串，显示在```多数据包```文本字段中。

   5. 当用户单击```Multi packet to data```按钮时，我们可以从```multi packet to data```文本字段中检索所有单独的数据，并将它们分别显示在 3 个文本字段（原始数据 1~原始数据 3）中。

   6. 您应该使用消息对话框来显示程序中引发的任何异常。下面是一个例子供您参考。
   
      ![image-20211217042934555](imgSources\image-20211217042934555.png)



# Part B

在这部分，你将开发一个学生管理系统来管理学生、课程、年级和教师的信息。

该系统有以下几个类：

1. 创建一个类```Course```，代表管理系统的课程信息。它应该至少有```courseId```和```courseName```两个成员变量。

2. 创建一个类```Person```。它至少有```name```和```gender```两个成员变量。性别应该是一个枚举类型，具有```MALE```和```FEMALE```两个值。

3. 创建一个继承自基类```Person```的类```Student```来表示学生信息。它至少有 1 个新成员变量```stuId```。

4. 创建一个继承自基类```Person```的类```Teacher```来表示教师信息。它至少有 3 个新的成员变量```teacherId```、```loginName```和```password```。

5. 创建一个```Grade```类来代表学生某个模块的成绩。它至少有```stuId```、```courseId```和```grade```三个成员变量，分别代表学生ID、课程ID和该学生的课程成绩。

6. 通过修复编译错误和适当的异常处理来完成```加密```课程，我们提供了大部分代码，用于加密和解密教师密码。

7. 创建```FileUtils```类，可以创建纯文本文件，分别保存学生、教师、课程和成绩对象。此外，它还应该提供从文件中检索对象的方法。为此，Student、Teacher、Course 和 Grade 类应实现 Serializable 接口。

8. 创建一个```DataAccess```类，它调用FileUtils 类来创建对象（Student、Course、Grade 和Teacher）并将其保存到4 个不同的纯文本文件中。

   该类应该使用 ArrayList 来保存从文件中检索到的对象。此外，它还可以根据对象的 id 搜索和返回对象。例如```Student getStudentById(String stuId)```方法可以通过studentId查找并返回一个student对象

9. 创建一个类```Q3```，在其main方法中实现如下功能。
   1. 创建操作菜单如下：

      ![image-20211217043431249](imgSources\image-20211217043431249.png)

   2. 用户可以输入一个数字来运行相关功能。

   3. 当用户输入 ```1``` 时，用户可以输入学生的 ID、姓名和性别来创建学生对象并将其附加到学生数据文件中。 用户可以连续创建多个学生对象，直到输入```-1```。 下图显示了用户创建了 2 个学生对象并将它们保存到学生数据文件中。

      ![image-20211217043518971](imgSources\image-20211217043518971.png)

      添加课程、教师和年级的操作与添加学生的操作类似。

   4. 当用户输入 5 时，所有学生对象应打印如下![image-20211217043613253](imgSources\image-20211217043613253.png)当没有对象时，应该打印如下信息让用户知道没有对象。
   
      ![image-20211217043702532](imgSources\image-20211217043702532.png)
   
      列出课程、教师和成绩对象的操作与列出学生对象的操作类似。
   
   5. 当用户输入 9 时，用户可以输入学生 ID 来搜索并打印出相关的学生对象。 如果学生对象不存在，则打印出一个字符串表示没有找到学生对象。![image-20211217043753159](imgSources\image-20211217043753159.png)搜索课程和教师对象的操作与搜索学生对象的操作类似。 但是，用户应该同时输入学生 ID 和课程 ID 来搜索成绩对象。
   
10. 其他要求如下：

    1. 程序可以持续运行，直到用户输入 0 退出。
    2. 您应该在创建对象时检查并避免重复的 Id。
    3. 异常应该被捕获并正确处理。
    4. Encryption 类中的密钥应更改为您的电子邮件地址。
    5. 方法和类的注释是必需的。

# Part C

在 C 部分，您将完成 B 部分程序的设计和文档编制，并提交一份 PDF 格式的报告。

## Javadoc 注释

在```FileUtils```类中编写适当的 Javadoc 注释，从中生成 Javadoc HTML，然后以 PDF 格式转换和提交 Javadoc。 

## 类图

在一个 PDF 文件中完成类 Person、Teacher 和 DataAccess 的 UML 类图

*javadoc 和类图应放在一个单独的 PDF 报告中提交。*
