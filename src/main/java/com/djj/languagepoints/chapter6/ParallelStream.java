package com.djj.languagepoints.chapter6;

import com.djj.languagepoints.data.Album;
import com.djj.languagepoints.data.SampleData;
import com.djj.languagepoints.data.Track;

/*
并行化流操作

如果已经有一个 Stream 对象，调用它的parallel 方法就能让其拥有并行操作的能力;
如果想从一个集合类创建一个流，调用parallelStream 就能立即获得一个拥有并行能力的流

并行化运行基于流的代码是否比串行化运行更快？
哪种方式花的时间更多取决于串行或并行化运行时的环境
 */
public class ParallelStream {
    public static void main(String[] args) {
        // 串行化计算专辑曲目长度
        SampleData.albums.flatMap(Album::getTracks).mapToInt(Track::getLength).sum();

        // 并行化计算专辑曲目长度
        SampleData.albums.parallel().flatMap(Album::getTracks).mapToInt(Track::getLength).sum();

        /*
        以上代码分析:
        在一个四核电脑上，
        如果有 10 张专辑，串行化代码的速度是并行化代码速度的 8 倍；
        如果将专辑数量增至 100 张，串行化和并行化速度相当；
        如果将专辑数量增值 10 000 张，则并行化代码的速度是串行化代码速度的 2.5 倍
         */
    }
}
